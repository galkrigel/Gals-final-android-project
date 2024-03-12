package com.example.final_project_android.Modules.Properties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.Adapter.PropertiesRecyclerAdapter
import com.example.final_project_android.R

class PropertyRecyclerViewActivity : AppCompatActivity() {
    private var propertiesRecyclerView: RecyclerView? = null
    var properties: MutableList<Property>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_rcycler_view)


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onPropertyClicked(property: Property?)
    }



}