package com.example.final_project_android.Modules.Properties.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.PropertyRecyclerViewActivity
import com.example.final_project_android.R

 class PropertiesRecyclerAdapter(var properties: List<Property>?) : RecyclerView.Adapter<PropertyViewHolder>() {

    var listener: PropertyRecyclerViewActivity.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.property_layout_row, parent, false)
        return PropertyViewHolder(itemView, listener, properties)

    }

    override fun getItemCount(): Int = properties?.size ?: 0
    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {

        val property = properties?.get(position)
        holder.bind(property)
    }
}