package com.home.container

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import navigation.Routes
import javax.inject.Inject

data class HomeContainerState(
    val isBottomBarVisible: Boolean = true,
    val currentRoute: String = Routes.Home.destination,
)

enum class HomeContainerEvent {
    UPDATE_ROUTE
}

@HiltViewModel
class HomeContainerViewModel @Inject constructor(): ViewModel() {

    private val _uiState : MutableStateFlow<HomeContainerState> =
        MutableStateFlow(HomeContainerState())
    val uiState = _uiState.asStateFlow()

    fun <T> onEvent(event: HomeContainerEvent, value: T) {
        when(event) {
            HomeContainerEvent.UPDATE_ROUTE -> updateCurrentRoute(value as String)
        }
    }

    private fun updateCurrentRoute(route: String) {
        _uiState.update { currentState ->
            return@update currentState.copy(currentRoute = route)
        }
    }
}

