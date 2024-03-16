package com.example.final_project_android.Modules.Properties

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.Adapter.PropertiesRecyclerAdapter
import com.example.final_project_android.R
import com.example.final_project_android.databinding.FragmentPropertiesBinding

class PropertiesFragment : Fragment() {
    var propertiesRecyclerView: RecyclerView? = null
    var properties: List<Property>? = null
    var adapter: PropertiesRecyclerAdapter? = null
    var progressBar: ProgressBar? = null

    private var _binding: FragmentPropertiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertiesBinding.inflate(inflater, container, false)
        val view = binding.root
        progressBar = binding.progressBar
        progressBar?.visibility = View.VISIBLE

        Model.instance.getAllProperties { properties ->
            this.properties = properties
            adapter?.properties = properties
            adapter?.notifyDataSetChanged()

            progressBar?.visibility = View.GONE

        }
        propertiesRecyclerView = binding.rvPropertiesFragmentList
        propertiesRecyclerView?.setHasFixedSize(true)
        propertiesRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = PropertiesRecyclerAdapter(properties)
        adapter?.listener = object : PropertyRecyclerViewActivity.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "Position clicked $position")
                val property = properties?.get(position)
                property?.let {
                    val action =
                        PropertiesFragmentDirections.actionPropertiesFragmentToBlueFragment(it.name)
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

        return view
    }

    override fun onResume() {
        super.onResume()

        progressBar?.visibility = View.VISIBLE
        Model.instance.getAllProperties { properties ->
            this.properties = properties
            adapter?.properties = properties
            adapter?.notifyDataSetChanged()

            progressBar?.visibility = View.GONE

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}