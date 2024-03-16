package com.example.final_project_android.Model

import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat
import com.example.final_project_android.dao.AppLocalDatabase
import com.example.final_project_android.dao.AppLocalDbRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.Executors

class Model private constructor() {

    private val database = AppLocalDatabase.db
    private var executer = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    private val firebaseModel = FirebaseModel()

    companion object {
        val instance: Model = Model()
    }

    interface GetAllPropertiesListener {
        fun onComplete(properties: List<Property>)
    }

    fun getAllProperties(callback: (List<Property>) -> Unit) {
        firebaseModel.getAllProperties(callback)
//        executer.execute {
//
//            Thread.sleep(5000)
//
//            val properties = database.propertyDao().getAll()
//            mainHandler.post {
//                callback(properties)
//            }
//        }
    }

    fun addProperty(property: Property, callback: () -> Unit) {
        firebaseModel.addProperty(property, callback)

//        executer.execute {
//            database.propertyDao().insert(property)
//            mainHandler.post {
//                callback()
//            }
//
//        }
    }

}
