package com.example.newapp.domain.categories

import com.example.newapp.di.DefaultDispatcher
import com.example.newapp.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class InsertCategoriesRepositoryUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val categoriesRepository: CategoriesRepository,
) : SuspendUseCase<String, Boolean, Unit>(dispatcher) {
    override suspend fun execute(i: String, t: Boolean) {
        return categoriesRepository.updateCategory(i, t)
    }
}