package com.example.newapp.data.remote.news

data class ArticlesModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)
