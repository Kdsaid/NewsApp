package com.example.newapp.domain.home

import com.example.newapp.data.remote.news.ArticlesModel
import com.example.newapp.di.DefaultDispatcher
import com.example.newapp.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class LoadHeadLinesRepositoryUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val countriesRepository: HeadLinesRepository,
) : SuspendUseCase<String, Unit, ArticlesModel>(dispatcher) {
    override suspend fun execute(i: String, t: Unit): ArticlesModel {
        return countriesRepository.getHeadlines(i)
    }
}