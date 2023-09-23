package com.example.newapp.presentation.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.common.Constants
import com.example.newapp.common.Constants.SELECTED_Category
import com.example.newapp.common.Constants.SELECTED_TO_HOME
import com.example.newapp.data.DatastoreImpl
import com.example.newapp.data.local.Category
import com.example.newapp.data.local.Country
import com.example.newapp.domain.categories.InsertCategoriesRepositoryUseCase
import com.example.newapp.domain.categories.LoadCategoriesRepositoryUseCase
import com.example.newapp.domain.categories.LoadFlowCategoriesRepositoryUseCase
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
    private val insertCategoriesRepositoryUseCase: InsertCategoriesRepositoryUseCase,
    private val loadFlowCategoriesRepositoryUseCase: LoadFlowCategoriesRepositoryUseCase,
    private val datastoreImpl: DatastoreImpl,
) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>>
        get() = _countries
    private val _categories = MutableStateFlow<
            List<Category>>(emptyList())
    val categories: StateFlow<List<Category>>
        get() = _categories
    private var _count = -1
    val countFlow = MutableStateFlow<Int>(-1)


    init {
        viewModelScope.launch {
            launch {
                loadCountries()

            }
            launch {
                loadCategories()

            }
            launch {
                loadFlowCategoriesRepositoryUseCase(Unit, Unit).onSuccess {
                    it.collect {
                        countFlow.value = it

                        _count = it
                    }

                }.onFailure {
                    //TODO this case will ba handled later when countries is used
                }
            }
        }
    }

    private suspend fun loadCountries() {
        loadCountriesUseCase(Unit, Unit).onSuccess {
            _countries.value = it
        }.onFailure {
            //TODO this case will ba handled later when countries is used
        }
    }

    private suspend fun loadCategories() {
        loadCategoriesRepositoryUseCase(Unit, Unit).onSuccess { it ->
            it.collect {
                _categories.value = it
            }
        }.onFailure {
            //TODO this case will ba handled later when countries is used
        }

    }

    fun selectedCountry(
        code: String
    ) = viewModelScope.launch {
        datastoreImpl.write(Constants.SELECTED_COUNTRY, code)
    }


    fun updateSubscribedCategory(
        value: String,
        isChecked: Boolean
    ) = viewModelScope.launch {

        if (isChecked) {
            if (_count < 3) {
                datastoreImpl.write(SELECTED_Category, value)
                insertCategoriesRepositoryUseCase.execute(
                    value,
                    true
                )

            }
        } else {

            insertCategoriesRepositoryUseCase.execute(
                value,
                false
            )
        }
    }

    fun toHome() = viewModelScope.launch {
        datastoreImpl.write(SELECTED_TO_HOME, true)

    }
}