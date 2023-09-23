package com.example.newapp.data.api

import com.example.newapp.BuildConfig.API_KEY
import com.example.newapp.common.Constants
import com.example.newapp.data.remote.news.ArticlesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(Constants.TOP_HEADLINE)
    suspend fun getHeadlines(
        @Query(Constants.API_KEY) apikey: String = API_KEY,
        @Query(Constants.QUERY_COUNTRY) countryCode: String = "us",
        @Query(Constants.QUERY_CATEGORY) category: String
    ): ArticlesModel

}