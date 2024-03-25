package com.example.final_project_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.ApiPropertyItem
import com.example.final_project_android.R

class ApiPropertiesRecyclerAdapter(var apiProperties: List<ApiPropertyItem>?) : RecyclerView.Adapter<ApiPropertyViewHolder>() {

    var listener: ApiPropertyRecyclerViewActivity.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiPropertyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.property_layout_row, parent, false)
        return ApiPropertyViewHolder(itemView, listener, apiProperties)
    }

    override fun getItemCount(): Int = apiProperties?.size ?: 0
    override fun onBindViewHolder(holder: ApiPropertyViewHolder, position: Int) {
        val property = apiProperties?.get(position)
        holder.bind(property)
    }
}