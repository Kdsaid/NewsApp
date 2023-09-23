package com.example.newapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newapp.common.localValue

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val code: String,
    val arabicName: String,
    val englishName: String,
    val isFollowed: Boolean
) {
    val categoryName: String
        get() = localValue(arabicName, englishName)
}
