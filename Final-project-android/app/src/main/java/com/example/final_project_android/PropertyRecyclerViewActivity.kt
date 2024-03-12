package com.example.final_project_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property

class PropertyRecyclerViewActivity : AppCompatActivity() {
    private var propertiesRecyclerView: RecyclerView? = null
    var properties: MutableList<Property>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_rcycler_view)

        properties = Model.instance.properties
        propertiesRecyclerView = findViewById(R.id.rvPropertiesRecyclerList)
        propertiesRecyclerView?.setHasFixedSize(true)

        propertiesRecyclerView?.layoutManager = LinearLayoutManager(this)
//        propertiesRecyclerView?.adapter = PropertiesRecyclerAdapter()

        val adapter = PropertiesRecyclerAdapter()
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "Position clicked $position")
            }

            override fun onPropertyClicked(property: Property?) {
                Log.i("TAG", "PROPERTY $property")
            }
        }

        propertiesRecyclerView?.adapter = adapter
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onPropertyClicked(property: Property?)
    }

    inner class PropertyViewHolder(val itemView: View, val listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView? = null
        var idTextView: TextView? = null
        var propertyCheckbox: CheckBox? = null
        var property: Property? = null

        init {
            nameTextView = itemView.findViewById(R.id.tvPropertiesListRowName)
            idTextView = itemView.findViewById(R.id.tvPropertiesListRowId)
            propertyCheckbox = itemView.findViewById(R.id.cbPropertiesListRow)

            propertyCheckbox?.setOnClickListener {
                var property = properties?.get(adapterPosition)
                property?.isChecked = propertyCheckbox?.isChecked ?: false
            }

            itemView.setOnClickListener {
                Log.i("TAG", "$adapterPosition")
                listener?.onItemClick(adapterPosition)
                listener?.onPropertyClicked(property)
            }
        }

        fun bind(property: Property?) {
            this.property = property
            nameTextView?.text = property?.name
            idTextView?.text = property?.id
            propertyCheckbox?.apply {
                isChecked = property?.isChecked ?: false

            }
        }
    }

    inner class PropertiesRecyclerAdapter : RecyclerView.Adapter<PropertyViewHolder>() {

        var listener: OnItemClickListener? = null
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.property_layout_row, parent, false)
            return PropertyViewHolder(itemView, listener)

        }

        override fun getItemCount(): Int = properties?.size ?: 0
        override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {

            val property = properties?.get(position)
            holder.bind(property)
        }
    }
}