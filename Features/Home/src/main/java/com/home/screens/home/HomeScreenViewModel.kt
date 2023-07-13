package com.home.screens.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entities.AutoComplete
import com.entities.Barber
import com.entities.BarberShop
import com.repositories.database.FirestoreRepository
import com.repositories.network.GeoCodingRepository
import commons.shouldSearchCompletions
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
data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val autoCompletePredictions: List<AutoComplete> = emptyList(),
    val address: String = "",
    val isAddressValid: Boolean = false,
    val barberShops: List<BarberShop> = emptyList(),
)

class HomeScreenEvent {
    enum class TypeEvent {
        NEW_SEARCH,
        SELECT_ADDRESS,
    }
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val geoCodingRepository: GeoCodingRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()
    private var job: Job? = null

    init {
        getBarbers()
    }

    private fun getBarbers() {
        startScreenLoading()
        firestoreRepository.getBarberShops(
            onResult = { barberShops ->
                _state.update { it.copy(barberShops = barberShops) }
                stopScreenLoading()
            },
        )
    }

    fun onTypeEvent(event: HomeScreenEvent.TypeEvent, text: String) {
        when (event) {
            HomeScreenEvent.TypeEvent.NEW_SEARCH -> searchPlaces(text)
            HomeScreenEvent.TypeEvent.SELECT_ADDRESS -> selectAutoComplete(text)
        }
    }

    private fun searchPlaces(search: String) {
        updateAddress(search)
        job?.cancel()
        clearAutoCompletePredictions()
        if (shouldSearchCompletions(search)) {
            job = viewModelScope.launch {
                delay(1000)
                val autoCompleteList = geoCodingRepository.getAutoCompletes(search)
                _state.update {
                    it.copy(autoCompletePredictions = autoCompleteList)
                }
            }
        }
    }

    private fun updateAddress(search: String) {
        _state.update {
            it.copy(
                address = search,
                isAddressValid = false
            )
        }
    }

    private fun selectAutoComplete(address: String) {
        clearAutoCompletePredictions()
        _state.update {
            it.copy(
                isAddressValid = true,
                address = address
            )
        }
    }

    private fun clearAutoCompletePredictions() {
        _state.update {
            it.copy(autoCompletePredictions = emptyList())
        }
    }

    private fun startScreenLoading() {
        _state.update {
            it.copy(isLoading = true)
        }
    }

    private fun stopScreenLoading() {
        _state.update {
            it.copy(isLoading = false)
        }
    }
}