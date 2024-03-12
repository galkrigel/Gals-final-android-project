package com.example.final_project_android.Model

class Model private constructor() {
    val properties: MutableList<Property> = ArrayList()

    companion object {
        val instance: Model = Model()
    }

    init {
        for (i in 0..20) {
            val property = Property("property $i", "id: ${i.toString()}", "https://me.com/avatar.jpg", false)
            properties.add(property)
        }
    }
}
