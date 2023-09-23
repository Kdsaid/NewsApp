package com.example.newapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newapp.data.local.Category

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}
