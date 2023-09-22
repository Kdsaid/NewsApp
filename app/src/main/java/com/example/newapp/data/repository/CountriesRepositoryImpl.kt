package com.example.newapp.data.repository

import com.example.newapp.data.local.countries
import com.example.newapp.domain.countries.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor() : CountriesRepository {
    override suspend fun getCountries() = countries
}