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
    val title: String,
    val country: String,
    val city: String,
    val price: String,
    val area: String,
    val ownerID: String,
    val imgUrl: String,
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
        const val TITLE_KEY = "title"
        const val OWNER_ID_KEY = "ownerID"
        const val COUNTRY_KEY = "country"
        const val CITY_KEY = "city"
        const val PRICE_KEY = "price"
        const val AREA_KEY = "area"
        const val IMG_URL_KEY = "imgUrl"
        const val LAST_UPDATED = "lastUpdated"
        const val GET_LAST_UPDATED = "get_last_updated"

        fun fromJson(json: Map<String, Any>): Property {
            val id = json[ID_KEY] as? String ?: ""
            val title = json[TITLE_KEY] as? String ?: ""
            val imgUrl = json[IMG_URL_KEY] as? String ?: ""
            val ownerID = json[OWNER_ID_KEY] as? String ?: ""
            val price = json[PRICE_KEY] as? String ?: ""
            val area = json[AREA_KEY] as? String ?: ""
            val country = json[COUNTRY_KEY] as? String ?: ""
            val city = json[CITY_KEY] as? String ?: ""
            val property = Property(id, title, country, city, price, area, ownerID, imgUrl)

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
                TITLE_KEY to title,
                IMG_URL_KEY to imgUrl,
                PRICE_KEY to price,
                COUNTRY_KEY to country,
                CITY_KEY to city,
                AREA_KEY to area,
                OWNER_ID_KEY to ownerID,
                LAST_UPDATED to FieldValue.serverTimestamp()
            )
        }
}