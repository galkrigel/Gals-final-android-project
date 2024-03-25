package com.example.final_project_android.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey
    val email: String,
    var name: String,
    var imgUrl: String,
) {
    constructor(email: String) : this(email, "", "")

    companion object {
        const val EMAIL_KEY = "email"
        const val NAME_KEY = "name"
        const val IMG_URL_KEY = "imgUrl"

        fun fromJson(json: Map<String, Any>): User {
            val email = json[EMAIL_KEY] as? String ?: ""
            val name = json[NAME_KEY] as? String ?: ""
            val imgUrl = json[IMG_URL_KEY] as? String ?: ""
            val user = User(email, name, imgUrl)
            return user
        }

        fun getUpdateMapWithImage(user: User): MutableMap<String, Any> {
            val updates = mutableMapOf<String, Any>()
            updates[NAME_KEY] = user.name
            updates[IMG_URL_KEY] = user.imgUrl
            return updates
        }

        fun getUpdateMap(user: User): MutableMap<String, Any> {
            val updates = mutableMapOf<String, Any>()
            updates[NAME_KEY] = user.name
            return updates
        }
    }




    val json: Map<String, Any>
        get() {
            return hashMapOf(
                EMAIL_KEY to email,
                NAME_KEY to name,
                IMG_URL_KEY to imgUrl,
            )
        }
}