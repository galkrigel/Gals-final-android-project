package com.example.final_project_android.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.final_project_android.Model.Property

@Dao
interface PropertyDao {
    @Query("SELECT * FROM Property")
    fun getAll(): LiveData<MutableList<Property>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg property: Property)

    @Delete
    fun delete(property: Property)

    @Query("SELECT * FROM Property WHERE id=:id")
    fun getPropertyById(id: String): LiveData<Property>

    @Query("SELECT * FROM Property WHERE ownerID=:ownerID")
    fun getPropertiesByOwnerId(ownerID: String): LiveData<MutableList<Property>>
}