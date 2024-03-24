package com.example.final_project_android.Modules.Properties

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.Adapter.PropertiesRecyclerAdapter
import com.example.final_project_android.R
import com.example.final_project_android.databinding.FragmentPropertiesBinding
import com.google.firebase.auth.FirebaseAuth

class UserPropertiesFragment : Fragment() {
    var propertiesRecyclerView: RecyclerView? = null
    var adapter: PropertiesRecyclerAdapter? = null
    var progressBar: ProgressBar? = null
    private lateinit var mAuth: FirebaseAuth


    private var _binding: FragmentPropertiesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PropertiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertiesBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[PropertiesViewModel::class.java]

        progressBar = binding.progressBar
        progressBar?.visibility = View.VISIBLE
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        viewModel.properties = Model.instance.getPropertiesByOwnerId(user!!.uid)

        propertiesRecyclerView = binding.rvPropertiesFragmentList
        propertiesRecyclerView?.setHasFixedSize(true)
        propertiesRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = PropertiesRecyclerAdapter(viewModel.properties?.value)

        adapter?.listener = object : PropertyRecyclerViewActivity.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "Position clicked $position")
                val property = viewModel.properties?.value?.get(position)
                property?.let {
                    // TODO change to price and all the others....
                    val action =
                        PropertiesFragmentDirections.actionPropertiesFragmentToBlueFragment(it.id, it.title, it.country, it.city, it.price, it.area, it.ownerID)
                    Navigation.findNavController(view).navigate(action)
                }
            }

            override fun onPropertyClicked(property: Property?) {
                Log.i("TAG", "PROPERTY $property")
            }
        }

        propertiesRecyclerView?.adapter = adapter

        val addPropertyButton: ImageButton =
            view.findViewById(R.id.ibtnPropertiesFragmentAddProperty)
        val action =
            Navigation.createNavigateOnClickListener(PropertiesFragmentDirections.actionGlobalAddPropertyFragment())
        addPropertyButton.setOnClickListener(action)

        viewModel.properties?.observe(viewLifecycleOwner) {
            adapter?.properties = it
            adapter?.notifyDataSetChanged()
            progressBar?.visibility = View.GONE
        }

        binding.pullToRefresh.setOnRefreshListener {
            reloadData()
        }

        Model.instance.propertiesListLoadingState.observe(viewLifecycleOwner) { state ->
            binding.pullToRefresh.isRefreshing = state == Model.LoadingState.LOADING
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        reloadData()
    }

    private fun reloadData() {

        progressBar?.visibility = View.VISIBLE
        Model.instance.refreshAllProperties()
        progressBar?.visibility = View.GONE

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}