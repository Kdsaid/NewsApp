package com.example.newapp.data.repository


import com.example.newapp.data.dao.AppDao
import com.example.newapp.data.local.Category
import com.example.newapp.data.local.categories
import com.example.newapp.domain.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val db: AppDao

) : CategoriesRepository {
    override suspend fun getCategories(): Flow<List<Category>> {
        db.insertCategory(categories.toList())
        return db.getCategories()
    }

    override suspend fun getFlowCategories(): Flow<Int> = db.getFollowedCategoriesCount()

    override suspend fun updateCategory(code: String, isChecked: Boolean) =
        db.updateCategory(code, isChecked)


}