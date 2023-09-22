package com.example.newapp.di

import com.example.newapp.data.repository.CategoriesRepositoryImpl
import com.example.newapp.data.repository.CountriesRepositoryImpl
import com.example.newapp.domain.categories.CategoriesRepository
import com.example.newapp.domain.countries.CountriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideCountriesRepository(
        countriesRepositoryImpl: CountriesRepositoryImpl
    ): CountriesRepository

    @Binds
    abstract fun provideCategoriesRepository(
        categoriesRepositoryImpl: CategoriesRepositoryImpl
    ): CategoriesRepository

}