package com.example.newapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.common.Constants
import com.example.newapp.data.DatastoreImpl
import com.example.newapp.data.remote.news.ArticlesModel
import com.example.newapp.domain.home.LoadHeadLinesRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadHeadLinesRepositoryUseCase: LoadHeadLinesRepositoryUseCase,


    private val datastore: DatastoreImpl
) : ViewModel() {


    private var category = ""

    init {
        viewModelScope.launch {
            category = datastore.read(Constants.SELECTED_Category, "")


        }
    }


    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState


    init {
        loadData()
    }

    private var job: Job? = null

    fun loadData() {
        job?.cancel()
        if (_uiState.value !is UiState.Loading) {
            _uiState.value = UiState.Loading
        }
        job = viewModelScope.launch {
            loadHeadLinesRepositoryUseCase(category, Unit).onSuccess { items ->
                _uiState.value = UiState.Success(items)
            }.onFailure {
                _uiState.value = UiState.Error(it.message ?: "Unknown Error")
            }
        }

    }


    sealed class UiState {
        object Loading : UiState()
        class Error(val msg: String) : UiState()
        class Success(val articlesModel: ArticlesModel) : UiState()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}