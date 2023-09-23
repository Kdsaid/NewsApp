package com.example.newapp.domain.categories

import com.example.newapp.data.local.Category
import com.example.newapp.di.DefaultDispatcher
import com.example.newapp.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LoadCategoriesRepositoryUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val categoriesRepository: CategoriesRepository,
) : SuspendUseCase<Unit, Unit, Flow<List<Category>>>(dispatcher) {
    override suspend fun execute(i: Unit, t: Unit): Flow<List<Category>> {
        return categoriesRepository.getCategories()
    }
}