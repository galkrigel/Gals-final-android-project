package com.example.final_project_android.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Property(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    var isChecked: Boolean
) {

    companion object {

        const val ID_KEY = "id"
        const val NAME_KEY = "name"
        const val IMAGE_URL_KEY = "imageUrl"
        const val IS_CHECKED_KEY = "isChecked"

        fun fromJson(json: Map<String, Any>): Property {
            val id = json[ID_KEY] as? String ?: ""
            val name = json[NAME_KEY] as? String ?: ""
            val imageUrl = json[IMAGE_URL_KEY] as? String ?: ""
            val isChecked = json[IS_CHECKED_KEY] as? Boolean ?: false
            return Property(id, name, imageUrl, isChecked)
        }
    }

    val json: Map<String, Any>
        get() {
            return hashMapOf(
                ID_KEY to id,
                NAME_KEY to name,
                IMAGE_URL_KEY to imageUrl,
                IS_CHECKED_KEY to isChecked
            )
        }
}