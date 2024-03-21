package com.example.final_project_android.Modules.ApiProperties

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.ApiPropertyItem
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.ApiProperties.ApiPropertyRecyclerViewActivity
import com.example.final_project_android.Modules.Properties.PropertyRecyclerViewActivity
import com.example.final_project_android.R

class ApiPropertyViewHolder(
    val itemView: View,
    val listener: ApiPropertyRecyclerViewActivity.OnItemClickListener?,
    var apiProperties: List<ApiPropertyItem>?
) :
    RecyclerView.ViewHolder(itemView) {
    var titleTextView: TextView? = null
    var idTextView: TextView? = null
    var priceTextView: TextView? = null
    var countryTextView: TextView? = null
    var cityTextView: TextView? = null
    var sizeTextView: TextView? = null
    var apiProperty: ApiPropertyItem? = null

    init {
        titleTextView = itemView.findViewById(R.id.tvPropertiesListRowName)
        idTextView = itemView.findViewById(R.id.tvPropertiesListRowId)


        itemView.setOnClickListener {
            Log.i("TAG", "$adapterPosition")
            listener?.onItemClick(adapterPosition)
            listener?.onApiPropertyClicked(apiProperty)
        }
    }

    fun bind(property: ApiPropertyItem?) {
        this.apiProperty = property
        titleTextView?.text = property?.title
        idTextView?.text = property?._id

    }
}