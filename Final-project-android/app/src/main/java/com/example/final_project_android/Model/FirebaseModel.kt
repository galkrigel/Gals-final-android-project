package com.example.final_project_android.Model

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase

class FirebaseModel {
    private val db = Firebase.firestore

    companion object {
        const val PROPERTIES_COLLECTION_PATH = "properties"
    }

    init {
        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings { })
        }
        db.firestoreSettings = settings
    }

    fun getAllProperties(since: Long, callback: (List<Property>) -> Unit) {

        db.collection(PROPERTIES_COLLECTION_PATH)
            .whereGreaterThanOrEqualTo(Property.LAST_UPDATED, Timestamp(since, 0))
            .get().addOnCompleteListener {
                when (it.isSuccessful) {
                    true -> {
                        val properties: MutableList<Property> = mutableListOf()
                        for (json in it.result) {
                            val property = Property.fromJson(json.data)
                            properties.add(property)
                        }
                        callback(properties)
                    }

                    false -> {
                        callback(listOf())
                    }
                }
            }
    }

    fun addProperty(property: Property, callback: () -> Unit) {
        // add users with id. with document(user.id)
        db.collection(PROPERTIES_COLLECTION_PATH).document().set(property.json)
            .addOnSuccessListener {
                callback()

            }

    }

}


//  val db = Firebase.firestore
//
//    // Create a new user with a first and last name
//    val property = hashMapOf(
//        "name" to "first property",
//        "city" to "Ashdod",
//    )
//
//// Add a new document with a generated ID
//    db.collection("properties")
//    .add(property)
//    .addOnSuccessListener { documentReference ->
//        Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
//    }
//    .addOnFailureListener { e ->
//        Log.w("TAG", "Error adding document", e)
//    }