package com.example.newapp.di

import com.example.newapp.BuildConfig.NEWS_BASE_URL
import com.example.newapp.common.ProviderOkHttpClient.providerOkHttpClient
import com.example.newapp.data.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideApiService(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(providerOkHttpClient())
            .build()
            .create(NewsApi::class.java)
    }
}