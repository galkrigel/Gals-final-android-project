package com.example.final_project_android.Modules.Properties.Adapter

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.PropertyRecyclerViewActivity
import com.example.final_project_android.R

class PropertyViewHolder(val itemView: View, val listener: PropertyRecyclerViewActivity.OnItemClickListener?, var properties: MutableList<Property>?) :
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