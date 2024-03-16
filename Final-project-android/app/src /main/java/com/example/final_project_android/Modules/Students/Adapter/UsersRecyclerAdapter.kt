package com.example.final_project_android.Modules.Students.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Student
import com.example.final_project_android.Modules.Students.UserRecyclerViewActivity
import com.example.final_project_android.R

 class UsersRecyclerAdapter(var users: MutableList<Student>?) : RecyclerView.Adapter<UserViewHolder>() {

    var listener: UserRecyclerViewActivity.OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_layout_row, parent, false)
        return UserViewHolder(itemView, listener, users)

    }

    override fun getItemCount(): Int = users?.size ?: 0
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = users?.get(position)
        holder.bind(user)
    }
}