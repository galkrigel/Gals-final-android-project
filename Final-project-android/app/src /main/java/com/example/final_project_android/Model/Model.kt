package com.example.final_project_android.Model

class Model private constructor() {
    val users: MutableList<Student> = ArrayList()

    companion object {
        val instance: Model = Model()
    }

    init {
        for (i in 0..20) {
            val user = Student("user $i", "id: ${i.toString()}", "https://me.com/avatar.jpg", false)
            users.add(user)
        }
    }
}
