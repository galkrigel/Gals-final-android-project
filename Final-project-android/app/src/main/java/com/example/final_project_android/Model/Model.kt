package com.example.final_project_android.Model

import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.final_project_android.dao.AppLocalDatabase
import com.example.final_project_android.dao.AppLocalDbRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.Executors

class Model private constructor() {
    enum class LoadingState {
        LOADING, LOADED
    }


    private val database = AppLocalDatabase.db
    private var executer = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    private val firebaseModel = FirebaseModel()
    private val properties: LiveData<MutableList<Property>>? = null
    val propertiesListLoadingState: MutableLiveData<LoadingState> =
        MutableLiveData(LoadingState.LOADED)

    companion object {
        val instance: Model = Model()
    }

    interface GetAllPropertiesListener {
        fun onComplete(properties: List<Property>)
    }

    fun getAllProperties(): LiveData<MutableList<Property>>? {
        refreshAllProperties()
        return properties ?: database.propertyDao().getAll()
    }

    fun getPropertiesByOwnerId(ownerId: String): LiveData<MutableList<Property>>? {
        refreshAllProperties()
        return database.propertyDao().getPropertiesByOwnerId(ownerId)
    }

    fun refreshAllProperties() {
        propertiesListLoadingState.value = LoadingState.LOADING


        val lastUpdated: Long = Property.lastUpdated
        firebaseModel.getAllProperties(lastUpdated) { list ->
            Log.i("TAG", "Firebase returned ${list.size}, lastUpdated: $lastUpdated")

            executer.execute {
                var time = lastUpdated
                for (property in list) {
                    database.propertyDao().insert(property)
                    property.lastUpdated?.let {
                        if (time < it)
                            time = property.lastUpdated ?: System.currentTimeMillis()
                    }
                }
                Property.lastUpdated = time
                propertiesListLoadingState.postValue(LoadingState.LOADED)


            }

        }

    }

    fun addProperty(property: Property, callback: () -> Unit) {
        firebaseModel.addProperty(property) {
            refreshAllProperties()
            callback()
        }
    }

}
