package com.example.final_project_android.Modules.Students

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
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Student
import com.example.final_project_android.R

class UsersListActivity : AppCompatActivity() {
    var propertiesListView: ListView? = null
    var properties: MutableList<Student>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)

        properties = Model.instance.properties

        propertiesListView = findViewById(R.id.lvUsersList)
        propertiesListView?.adapter = PropertiesListAdapter(properties)

        propertiesListView?.setOnItemClickListener { parent, view, position, id ->
            Log.i("TAG", "Row was clicked at: $position")
        }
    }

    class PropertiesListAdapter(val properties: MutableList<Student>?) : BaseAdapter() {
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
                val propertyCheckbox: CheckBox? = view?.findViewById(R.id.cbPropertiesListRow)
                propertyCheckbox?.setOnClickListener {

                    (propertyCheckbox?.tag as? Int)?.let {tag ->
                        var property = properties?.get(tag)
                        property?.isChecked = propertyCheckbox?.isChecked ?: false
                    }
                }
            }

            view = view ?: convertView

            val nameTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowName)
            val idTextView: TextView? = view?.findViewById(R.id.tvPropertiesListRowId)
            val propertyCheckbox: CheckBox? = view?.findViewById(R.id.cbPropertiesListRow)

            nameTextView?.text = property?.name
            idTextView?.text = property?.id
            propertyCheckbox?.apply {
                isChecked = property?.isChecked ?: false
                tag = position
            }

            return view!!
        }

    }
}