package com.example.final_project_android.Model

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.final_project_android.base.MyApplication
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

@Entity
class User(
    @PrimaryKey val email: String,
    val id: String,
    val name: String,
    val surname: String,
    val city: String,
    val country: String,
    val imgUrl: String,
) {

    companion object {
        const val EMAIL_KEY = "id"
        const val ID_KEY = "id"
        const val NAME_KEY = "title"
        const val SURNAME_KEY = "surname"
        const val IMG_URL_KEY = "imgUrl"
        const val CITY_KEY = "city"
        const val COUNTRY_KEY = "country"

        fun fromJson(json: Map<String, Any>): User {
            val id = json[ID_KEY] as? String ?: ""
            val email = json[EMAIL_KEY] as? String ?: ""
            val name = json[NAME_KEY] as? String ?: ""
            val surname = json[SURNAME_KEY] as? String ?: ""
            val country = json[COUNTRY_KEY] as? String ?: ""
            val city = json[CITY_KEY] as? String ?: ""
            val imgUrl = json[IMG_URL_KEY] as? String ?: ""
            val user = User(email, id, name, surname, city, country, imgUrl)
            return user
        }
    }

    val json: Map<String, Any>
        get() {
            return hashMapOf(
                ID_KEY to id,
                EMAIL_KEY to email,
                NAME_KEY to name,
                SURNAME_KEY to surname,
                COUNTRY_KEY to country,
                CITY_KEY to city,
                IMG_URL_KEY to imgUrl,
            )
        }
}