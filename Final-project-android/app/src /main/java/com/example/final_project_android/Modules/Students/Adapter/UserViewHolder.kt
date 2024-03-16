package com.example.final_project_android.Modules.Students.Adapter

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Student
import com.example.final_project_android.Modules.Students.UserRecyclerViewActivity
import com.example.final_project_android.R

class UserViewHolder(val itemView: View, val listener: UserRecyclerViewActivity.OnItemClickListener?, var users: MutableList<Student>?) :
    RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView? = null
    var idTextView: TextView? = null
    var userCheckbox: CheckBox? = null
    var user: Student? = null

    init {
        nameTextView = itemView.findViewById(R.id.tvPropertiesListRowName)
        idTextView = itemView.findViewById(R.id.tvPropertiesListRowId)
        userCheckbox = itemView.findViewById(R.id.cbPropertiesListRow)

        userCheckbox?.setOnClickListener {
            var user = users?.get(adapterPosition)
            user?.isChecked = userCheckbox?.isChecked ?: false
        }

        itemView.setOnClickListener {
            Log.i("TAG", "$adapterPosition")
            listener?.onItemClick(adapterPosition)
            listener?.onUserClicked(user)
        }
    }

    fun bind(user: Student?) {
        this.user = user
        nameTextView?.text = user?.name
        idTextView?.text = user?.id
        userCheckbox?.apply {
            isChecked = user?.isChecked ?: false

        }
    }
}