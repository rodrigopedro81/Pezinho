package com.home.screens.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@Stable
data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
)

enum class HomeEvent {
}

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _uiState : MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val uiState : StateFlow<HomeState> = _uiState.asStateFlow()


}