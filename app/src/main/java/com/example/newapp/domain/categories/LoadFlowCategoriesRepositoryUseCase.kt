package com.example.newapp.domain.categories

import com.example.newapp.di.DefaultDispatcher
import com.example.newapp.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LoadFlowCategoriesRepositoryUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val categoriesRepository: CategoriesRepository,
) : SuspendUseCase<Unit, Unit, Flow<Int>>(dispatcher) {
    override suspend fun execute(i: Unit, t: Unit): Flow<Int> {
        return categoriesRepository.getFlowCategories()
    }
}