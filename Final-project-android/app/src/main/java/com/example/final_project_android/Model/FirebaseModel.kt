package com.example.final_project_android.Model

import android.net.Uri
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import java.util.concurrent.Executors
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class FirebaseModel {
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()
    private var executor = Executors.newSingleThreadExecutor()
    private var storage: FirebaseStorage? = null
    private var storageRef: StorageReference? = null

    companion object {
        const val PROPERTIES_COLLECTION_PATH = "properties"
    }

    init {
        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings { })
        }
        db.firestoreSettings = settings
        storage = Firebase.storage
        storageRef = storage!!.reference
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
                            print("PROPERTY: " + property + property.title);
                            properties.add(property)
                        }
                        print("length: " + properties.size)
                        Log.i("LENGTH", "${properties.size}")
                        callback(properties)
                    }

                    false -> {
                        callback(listOf())
                    }
                }
            }
    }

    private fun uploadImage(
        imageUri: Uri,
        filename: String,
        callback: (String?) -> Unit
    ) {
        val imageRef = storageRef?.child(filename)
        Log.i("image2-uploadImage", "${filename}")
        // Upload file to Firebase Storage
        imageRef?.putFile(imageUri)
            ?.addOnSuccessListener { _ ->
                // Image uploaded successfully
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    Log.i("image3-uploadImage", "${filename}")
                    callback(uri.toString())
                }
            }
            ?.addOnFailureListener { e ->
                Log.e("TAG", "Failed to upload image", e)
                callback(null)
            }
    }

    fun deleteProperty(
        propertyId: String,
        callback: (Boolean) -> Unit
    ){
        val postDocumentRef = db.collection(PROPERTIES_COLLECTION_PATH).document(propertyId)

        postDocumentRef.delete()
            .addOnSuccessListener {
                Log.i("TAG", "Property with ID $propertyId deleted successfully")
                callback(true)
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Failed to delete property with ID $propertyId, Error: $exception")
                callback(false)
            }
    }
    fun addProperty(
        property: Property,
        imageUri: Uri?,
        callback: () -> Unit
    ) {
        if (imageUri != null) {
            val userId = auth.currentUser?.uid
            val timestamp = System.currentTimeMillis()
            uploadImage(imageUri, "images/$userId/$timestamp.jpg") { imageUrl ->
                if (imageUrl != null) {
                    var newProperty = Property(
                        property.id,
                        property.title,
                        property.country,
                        property.city,
                        property.price,
                        property.area,
                        property.ownerID,
                        imageUrl
                    )
                    Log.i("FirebaseModel add property1: ", "${imageUrl}")

                    db.collection(PROPERTIES_COLLECTION_PATH).document(newProperty.id).set(newProperty.json)
                        .addOnSuccessListener {
                            callback()
                        }
                } else {
                    Log.i("FirebaseModel add property2: ", "Image upload failed")
                    db.collection(PROPERTIES_COLLECTION_PATH).document(property.id).set(property.json)
                        .addOnSuccessListener {
                            callback()
                        }
                }
            }

        } else {
            Log.i("FirebaseModel add property", "Add property without an image")
            db.collection(PROPERTIES_COLLECTION_PATH).document(property.id).set(property.json)
                .addOnSuccessListener {
                    callback()
                }
        }
    }
}