package com.example.final_project_android.Modules.Students

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Student
import com.example.final_project_android.Modules.Students.Adapter.UsersRecyclerAdapter
import com.example.final_project_android.R

class UsersFragment : Fragment() {
    private var propertiesRecyclerView: RecyclerView? = null
    var properties: MutableList<Student>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_properties, container, false)

        properties = Model.instance.properties
        propertiesRecyclerView = view.findViewById(R.id.rvPropertiesFragmentList)
        propertiesRecyclerView?.setHasFixedSize(true)

        propertiesRecyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = UsersRecyclerAdapter(properties)
        adapter.listener = object : UserRecyclerViewActivity.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "Position clicked $position")
            }

            override fun onPropertyClicked(property: Student?) {
                Log.i("TAG", "PROPERTY $property")
            }
        }

        propertiesRecyclerView?.adapter = adapter

        return view
    }


}