package com.example.newapp.domain.categories

import com.example.newapp.data.local.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Flow<List<Category>>
    suspend fun getFlowCategories(): Flow<Int>
    suspend fun updateCategory(code: String, isChecked: Boolean)

}