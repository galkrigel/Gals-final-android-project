package com.example.final_project_android.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.final_project_android.Model.Property

@Dao
interface PropertyDao {
    @Query("SELECT * FROM Property")
    fun getAll(): List<Property>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetProperty(vararg property: Property)

    @Delete
    fun delete(property: Property)

    @Query("SELECT * FROM Property WHERE id=:id")
    fun getPropertyById(id: String): Property
}