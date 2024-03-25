package com.example.final_project_android.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Property
import com.example.final_project_android.databinding.ActivityPropertyRcyclerViewBinding

class PropertyRecyclerViewActivity : AppCompatActivity() {
    var propertiesRecyclerView: RecyclerView? = null
    var properties: List<Property>? = null
    var adapter: PropertiesRecyclerAdapter? = null

    private lateinit var binding: ActivityPropertyRcyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyRcyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        propertiesRecyclerView = binding.rvPropertiesRecyclerList
        propertiesRecyclerView?.setHasFixedSize(true)
        propertiesRecyclerView?.layoutManager = LinearLayoutManager(this)

        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "PropertiesRecyclerAdapter: Position clicked: $position")
            }

            override fun onPropertyClicked(property: Property?) {
                Log.i("TAG", "PROPERTY $property")
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onPropertyClicked(property: Property?)
    }

    override fun onResume() {
        super.onResume()

//        Model.instance.getAllProperties { properties ->
//            this.properties = properties
//            adapter?.properties = properties
//            adapter?.notifyDataSetChanged()
//        }
    }
}