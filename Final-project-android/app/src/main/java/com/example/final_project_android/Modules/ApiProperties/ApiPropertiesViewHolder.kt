package com.example.final_project_android.Modules.ApiProperties

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.ApiPropertyItem
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
    var areaTextView: TextView? = null
    var apiProperty: ApiPropertyItem? = null

    init {
        titleTextView = itemView.findViewById(R.id.tvPropertiesListRowTitle)
        priceTextView = itemView.findViewById(R.id.tvPropertiesListRowPrice)
        areaTextView = itemView.findViewById(R.id.tvPropertiesListRowArea)
        countryTextView = itemView.findViewById(R.id.tvPropertiesListRowCountry)
        cityTextView = itemView.findViewById(R.id.tvPropertiesListRowCity)


        itemView.setOnClickListener {
            Log.i("TAG", "$adapterPosition")
            listener?.onItemClick(adapterPosition)
            listener?.onApiPropertyClicked(apiProperty)
        }
    }

    fun bind(property: ApiPropertyItem?) {
        this.apiProperty = property
        titleTextView?.text = "Title: ${property?.title}"
        countryTextView?.text = "Country: ${property?.country}"
        cityTextView?.text = "City: ${property?.city}"
        priceTextView?.text = "Price: ${property?.price}$"
        areaTextView?.text = "Area: ${property?.area} m^2"

    }
}