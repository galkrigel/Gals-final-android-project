package com.example.final_project_android.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.final_project_android.Model.Property
import com.example.final_project_android.base.MyApplication


@Database(entities = [Property::class], version = 3)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
}

object AppLocalDatabase {

    val db: AppLocalDbRepository by lazy {

        val context = MyApplication.Globals.appContext
            ?: throw IllegalStateException("Application context not available")

        Room.databaseBuilder(
            context,
            AppLocalDbRepository::class.java,
            "dbFileName.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}