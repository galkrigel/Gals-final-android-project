package com.example.final_project_android.Modules.Students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Student
import com.example.final_project_android.R

class UserRecyclerViewActivity : AppCompatActivity() {
    private var propertiesRecyclerView: RecyclerView? = null
    var properties: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_rcycler_view)


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onPropertyClicked(property: Student?)
    }



}