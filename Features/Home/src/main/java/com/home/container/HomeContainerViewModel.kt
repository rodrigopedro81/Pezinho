package com.home.container

import androidx.lifecycle.ViewModel
import com.entities.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import navigation.Routes

data class HomeContainerState(
    val isBottomBarVisible: Boolean = true,
    val currentRoute: String = Routes.Home.destination,
    val currentLocation: Location = Location(0, 0)
)

enum class HomeContainerEvent {
    UPDATE_LOCATION,
    UPDATE_ROUTE
}

@HiltViewModel
class HomeContainerViewModel: ViewModel() {

    private val _uiState : MutableStateFlow<HomeContainerState> =
        MutableStateFlow(HomeContainerState())
    val uiState = _uiState.asStateFlow()

    fun <T> onEvent(event: HomeContainerEvent, value: T) {
        when(event) {
            HomeContainerEvent.UPDATE_LOCATION -> updateCurrentLocation(value as Location)
            HomeContainerEvent.UPDATE_ROUTE -> updateCurrentRoute(value as String)
        }
    }

    private fun updateCurrentLocation(location: Location) {
        _uiState.update { currentState ->
            return@update currentState.copy(currentLocation = location)
        }
    }

    private fun updateCurrentRoute(route: String) {
        _uiState.update { currentState ->
            return@update currentState.copy(currentRoute = route)
        }
    }
}

