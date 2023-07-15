package com.home.screens.barberList

import androidx.lifecycle.ViewModel
import com.entities.BarberShop
import com.repositories.database.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class BarberListScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val barberShops: List<BarberShop> = emptyList(),
    val selectedBarberShop: BarberShop? = null,
)

@HiltViewModel
class BarberListScreenViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state: MutableStateFlow<BarberListScreenState> = MutableStateFlow(BarberListScreenState())
    val state: StateFlow<BarberListScreenState> = _state.asStateFlow()

    init {
        getBarbers()
    }

//    fun onClickEvent(event: HomeScreenEvent.ClickEvent, selectedBarberShop: BarberShop) {
//        when (event) {
//            HomeScreenEvent.ClickEvent.SELECTED_BARBERSHOP -> {
//                _state.update {
//                    it.copy(selectedBarberShop = selectedBarberShop)
//                }
//            }
//        }
//    }

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