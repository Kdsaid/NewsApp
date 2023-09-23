package com.example.newapp.domain.home

import com.example.newapp.data.remote.news.ArticlesModel


interface HeadLinesRepository {
    suspend fun getHeadlines(category: String): ArticlesModel
}