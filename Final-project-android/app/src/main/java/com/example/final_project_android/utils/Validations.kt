package com.example.final_project_android.utils


import android.util.Patterns

object Validations {

    fun isFieldEmpty(field: String): Boolean {
        return field.isEmpty() || field == ""
    }
}