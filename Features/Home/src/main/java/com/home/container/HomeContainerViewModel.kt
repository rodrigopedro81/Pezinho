package com.home.container

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeContainerState(
    val isBottomBarVisible: Boolean = true,
)

enum class HomeContainerEvent {

}

@HiltViewModel
class HomeContainerViewModel @Inject constructor(): ViewModel() {

    private val _uiState : MutableStateFlow<HomeContainerState> =
        MutableStateFlow(HomeContainerState())
    val uiState = _uiState.asStateFlow()

}

