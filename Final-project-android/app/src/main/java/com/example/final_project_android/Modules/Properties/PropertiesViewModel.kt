package com.example.final_project_android.Modules.Properties

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.final_project_android.Model.Property

class PropertiesViewModel: ViewModel() {
    var properties: LiveData<MutableList<Property>>? = null
}