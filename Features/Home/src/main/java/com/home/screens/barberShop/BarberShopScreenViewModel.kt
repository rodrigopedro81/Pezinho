package com.home.screens.barberShop

import androidx.lifecycle.ViewModel
import com.entities.AvailableService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


data class BarberShopScreenState(
    val selectedServices: List<AvailableService> = listOf()
)

@HiltViewModel
class BarberShopScreenViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<BarberShopScreenState> =
        MutableStateFlow(BarberShopScreenState())
    val state = _state.asStateFlow()
}
