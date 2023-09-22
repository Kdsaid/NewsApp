package com.example.newapp.domain.categories

import com.example.newapp.data.local.Category
import com.example.newapp.di.DefaultDispatcher
import com.example.newapp.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class LoadCategoriesRepositoryUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val categoriesRepository: CategoriesRepository,
) : SuspendUseCase<Unit, List<Category>>(dispatcher) {
    override suspend fun execute(i: Unit): List<Category> {
        return categoriesRepository.getCategories()
    }
}