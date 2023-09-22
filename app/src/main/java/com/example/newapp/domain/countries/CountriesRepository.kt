package com.example.newapp.domain.countries

import com.example.newapp.data.local.Country

interface CountriesRepository {
    suspend fun getCountries(): List<Country>

}