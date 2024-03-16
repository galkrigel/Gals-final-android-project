package com.example.final_project_android.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Property(
    @PrimaryKey val id: String,
    val name: String,
    val avatar: String,
    var isChecked: Boolean
) {
}