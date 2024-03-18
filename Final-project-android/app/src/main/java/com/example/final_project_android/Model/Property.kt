package com.example.final_project_android.Model

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.final_project_android.base.MyApplication
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

@Entity
class Property(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    var isChecked: Boolean,
    var lastUpdated: Long? = null
) {

    companion object {

        var lastUpdated: Long
            get() {
                return MyApplication.Globals?.appContext?.getSharedPreferences(
                    "TAG",
                    Context.MODE_PRIVATE
                )
                    ?.getLong(GET_LAST_UPDATED, 0) ?: 0
            }
            set(value) {
                MyApplication.Globals?.appContext?.getSharedPreferences("TAG", Context.MODE_PRIVATE)
                    ?.edit()?.putLong(
                        GET_LAST_UPDATED, value
                    )?.apply()
            }

        const val ID_KEY = "id"
        const val NAME_KEY = "name"
        const val IMAGE_URL_KEY = "imageUrl"
        const val IS_CHECKED_KEY = "isChecked"
        const val LAST_UPDATED = "lastUpdated"
        const val GET_LAST_UPDATED = "get_last_updated"

        fun fromJson(json: Map<String, Any>): Property {
            val id = json[ID_KEY] as? String ?: ""
            val name = json[NAME_KEY] as? String ?: ""
            val imageUrl = json[IMAGE_URL_KEY] as? String ?: ""
            val isChecked = json[IS_CHECKED_KEY] as? Boolean ?: false
            val property = Property(id, name, imageUrl, isChecked)

            val timestamp: Timestamp? = json[LAST_UPDATED] as? Timestamp
            timestamp?.let {
                property.lastUpdated = it.seconds
            }

            return property
        }
    }

    val json: Map<String, Any>
        get() {
            return hashMapOf(
                ID_KEY to id,
                NAME_KEY to name,
                IMAGE_URL_KEY to imageUrl,
                IS_CHECKED_KEY to isChecked,
                LAST_UPDATED to FieldValue.serverTimestamp()
            )
        }
}