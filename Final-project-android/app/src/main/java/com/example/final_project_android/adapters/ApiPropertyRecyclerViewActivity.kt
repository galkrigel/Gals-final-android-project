package com.example.final_project_android.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.ApiPropertyItem
import com.example.final_project_android.Model.Property
import com.example.final_project_android.databinding.ActivityApiPropertyRecyclerViewBinding

class ApiPropertyRecyclerViewActivity : AppCompatActivity() {
    var apiPropertiesRecyclerView: RecyclerView? = null
    var apiProperties: List<Property>? = null
    var adapter: ApiPropertiesRecyclerAdapter? = null

    private lateinit var binding: ActivityApiPropertyRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiPropertyRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiPropertiesRecyclerView = binding.rvApiPropertiesRecyclerList
        apiPropertiesRecyclerView?.setHasFixedSize(true)
        apiPropertiesRecyclerView?.layoutManager = LinearLayoutManager(this)

        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "PropertiesRecyclerAdapter: Position clicked: $position")
            }

            override fun onApiPropertyClicked(property: ApiPropertyItem?) {
                Log.i("TAG", "PROPERTY $property")
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onApiPropertyClicked(property: ApiPropertyItem?)
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