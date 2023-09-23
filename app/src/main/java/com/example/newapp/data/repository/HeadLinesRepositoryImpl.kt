package com.example.newapp.data.repository


import com.example.newapp.data.api.NewsApi
import com.example.newapp.data.remote.news.ArticlesModel
import com.example.newapp.domain.home.HeadLinesRepository
import javax.inject.Inject

class HeadLinesRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : HeadLinesRepository {
    override suspend fun getHeadlines(category: String): ArticlesModel {
        return newsApi.getHeadlines(category = category)
    }
}