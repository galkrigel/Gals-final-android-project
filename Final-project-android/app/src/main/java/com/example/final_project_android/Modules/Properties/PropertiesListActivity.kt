package com.example.final_project_android.Modules.Properties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import com.example.final_project_android.Model.Property
import com.example.final_project_android.R

class PropertiesListActivity : AppCompatActivity() {
    var propertiesListView: ListView? = null
    var properties: List<Property>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)

//        Model.instance.getAllProperties { properties ->
//            this.properties = properties
//        }

        propertiesListView = findViewById(R.id.lvPropertiesList)
        propertiesListView?.adapter = PropertiesListAdapter(properties)

        propertiesListView?.setOnItemClickListener { parent, view, position, id ->
            Log.i("TAG", "Row was clicked at: $position")
        }
    }

    class PropertiesListAdapter(val properties: List<Property>?) : BaseAdapter() {
        override fun getCount(): Int = properties?.size ?: 0

        override fun getItem(p0: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(p0: Int): Long = 10

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val property = properties?.get(position)
            var view: View? = null
            if (convertView == null) {
                view = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.property_layout_row, parent, false)
//                val propertyCheckbox: CheckBox? = view?.findViewById(R.id.cbPropertiesListRow)
//                propertyCheckbox?.setOnClickListener {
//
//                    (propertyCheckbox?.tag as? Int)?.let {tag ->
//                        var property = properties?.get(tag)
//                        property?.isChecked = propertyCheckbox?.isChecked ?: false
//                    }
//                }
            }

            view = view ?: convertView

            val titleTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowTitle)
            val countryTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowCountry)
            val cityTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowCity)
            val priceTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowPrice)
            val areaTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowArea)

//            val idTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowId)
//            val propertyCheckbox: CheckBox? = view?.findViewById(R.id.cbPropertiesListRow)

            titleTextView?.text = property?.title
            countryTextView?.text = property?.country
            cityTextView?.text = property?.city
            priceTextView?.text = property?.price
            areaTextView?.text = property?.area

//            idTextView?.text = property?.id
//            propertyCheckbox?.apply {
//                isChecked = property?.isChecked ?: false
//                tag = position
//            }

            return view!!
        }

    }
}