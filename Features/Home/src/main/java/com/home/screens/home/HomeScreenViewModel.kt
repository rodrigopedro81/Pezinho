package com.home.screens.home

import androidx.lifecycle.ViewModel
import com.entities.BarberShop
import com.repositories.database.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val barberShops: List<BarberShop> = emptyList(),
    val selectedBarberShop: BarberShop? = null,
)

interface HomeScreenEvent {

    enum class ClickEvent: HomeScreenEvent {
        SELECTED_BARBERSHOP
    }
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        getBarbers()
    }

    fun onClickEvent(event: HomeScreenEvent.ClickEvent, selectedBarberShop: BarberShop) {
        when (event) {
            HomeScreenEvent.ClickEvent.SELECTED_BARBERSHOP -> {
                _state.update {
                    it.copy(selectedBarberShop = selectedBarberShop)
                }
            }
        }
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