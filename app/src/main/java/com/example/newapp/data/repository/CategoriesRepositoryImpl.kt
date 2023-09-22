package com.example.newapp.data.repository


import com.example.newapp.data.local.categories
import com.example.newapp.domain.categories.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor() : CategoriesRepository {
    override suspend fun getCategories() = categories
}