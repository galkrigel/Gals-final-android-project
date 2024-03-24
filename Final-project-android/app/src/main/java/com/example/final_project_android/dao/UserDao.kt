package com.example.final_project_android.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Query("DELETE FROM User WHERE email = :email")
    fun delete(email: String)

    @Query("SELECT * FROM User WHERE email=:email ")
    fun getUserByEmail(email: String): User?
}