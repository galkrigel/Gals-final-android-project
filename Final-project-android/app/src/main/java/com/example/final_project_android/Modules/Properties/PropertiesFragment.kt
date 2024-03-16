package com.example.final_project_android.Modules.Properties

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.Adapter.PropertiesRecyclerAdapter
import com.example.final_project_android.R

class PropertiesFragment : Fragment() {
    private var propertiesRecyclerView: RecyclerView? = null
    var properties: MutableList<Property>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_properties, container, false)

        properties = Model.instance.properties
        propertiesRecyclerView = view.findViewById(R.id.rvPropertiesFragmentList)
        propertiesRecyclerView?.setHasFixedSize(true)

        propertiesRecyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = PropertiesRecyclerAdapter(properties)
        adapter.listener = object : PropertyRecyclerViewActivity.OnItemClickListener {
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


}