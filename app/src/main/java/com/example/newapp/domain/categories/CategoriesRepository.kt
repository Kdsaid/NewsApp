package com.example.newapp.domain.categories

import com.example.newapp.data.local.Category

interface CategoriesRepository {
    suspend fun getCategories(): List<Category>
}