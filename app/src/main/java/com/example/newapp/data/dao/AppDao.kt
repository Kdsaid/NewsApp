package com.example.newapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.newapp.data.local.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {


    @Query("select * from categories")
    fun getCategories(): Flow<List<Category>>

    @Query("select COUNT(code) from categories where isFollowed")
    fun getFollowedCategoriesCount(): Flow<Int>

    @Query("select * from categories where isFollowed")
    fun getFollowedCategories(): Flow<List<Category>>

    @Query("update categories set isFollowed = :isChecked where code = :code")
    suspend fun updateCategory(code: String, isChecked: Boolean)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(country: List<Category>)


}