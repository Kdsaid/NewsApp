package com.example.newapp.domain.countries

import com.example.newapp.data.local.Country
import com.example.newapp.di.DefaultDispatcher
import com.example.newapp.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class LoadCountriesRepositoryUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val countriesRepository: CountriesRepository,
) : SuspendUseCase<Unit, Unit, List<Country>>(dispatcher) {
    override suspend fun execute(i: Unit, t: Unit): List<Country> {
        return countriesRepository.getCountries()
    }
}