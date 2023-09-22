package com.example.newapp.presentation.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.data.local.Category
import com.example.newapp.data.local.Country
import com.example.newapp.domain.categories.LoadCategoriesRepositoryUseCase
import com.example.newapp.domain.countries.LoadCountriesRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val loadCountriesUseCase: LoadCountriesRepositoryUseCase,
    private val loadCategoriesRepositoryUseCase: LoadCategoriesRepositoryUseCase,
) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>>
        get() = _countries
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>>
        get() = _categories

    init {
        loadCountries()
        loadCategories()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            loadCountriesUseCase(Unit).onSuccess {
                _countries.value = it
            }.onFailure {
                //TODO this case will ba handled later when countries is used
            }
        }
    }   private fun loadCategories() {
        viewModelScope.launch {
            loadCategoriesRepositoryUseCase(Unit).onSuccess {
                _categories.value = it
            }.onFailure {
                //TODO this case will ba handled later when countries is used
            }
        }
    }

}