package com.home.screens.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.repositories.GeoCodingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val autoCompletePredictions: List<String> = emptyList(),
    val selectedAddress: Boolean = false,
    val address: String = "",
)

class HomeEvent {
    enum class TypeEvent {
        NEW_SEARCH,
        SELECT_ADDRESS,
    }
}


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val geoCodingRepository: GeoCodingRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()
    private var job: Job? = null

    fun onTypeEvent(event: HomeEvent.TypeEvent, text: String) {
        when (event) {
            HomeEvent.TypeEvent.NEW_SEARCH -> searchPlaces(text)
            HomeEvent.TypeEvent.SELECT_ADDRESS -> {
                clearAutoCompletePredictions()
                selectAutoComplete(text)
            }
        }
    }

    private fun searchPlaces(search: String) {
        updateAddress(search)
        job?.cancel()
        clearAutoCompletePredictions()
        if (shouldSearchCompletions(search)) {
            job = viewModelScope.launch {
                delay(1000)
                setAutoCompletePredictions(geoCodingRepository.getAutoCompletes(search))
            }
        }
    }

    private fun shouldSearchCompletions(search: String): Boolean =
        with(search.withoutBlank){
            when {
                length < 4 -> false
                lowercase().contains("rua") && length < 6 -> false
                else -> true
            }
        }

    // remove blank from string
    private val String.withoutBlank: String
        get() = replace(" ", "")

    private fun updateAddress(search: String) {
        _uiState.update {
            it.copy(
                address = search,
                selectedAddress = false
            )
        }
    }

    private fun selectAutoComplete(address: String) {
        _uiState.update {
            it.copy(
                selectedAddress = true,
                address = address
            )
        }
    }

    private fun clearAutoCompletePredictions() {
        _uiState.update {
            it.copy(autoCompletePredictions = emptyList())
        }
    }

    private fun setAutoCompletePredictions(autoCompleteList: List<String>) {
        _uiState.update {
            it.copy(autoCompletePredictions = autoCompleteList)
        }
    }

    private fun startScreenLoading() {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    private fun stopScreenLoading() {
        _uiState.update {
            it.copy(isLoading = false)
        }
    }
}