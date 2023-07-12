package com.home.screens.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun ProfileScreen(viewModel: ProfileScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    ProfileScreenContent(
        state = state.value
    )
}

@Composable
fun ProfileScreenContent(state: ProfileScreenState) {

}

data class ProfileScreenState(val x: Int = 0)

class ProfileScreenViewModel : ViewModel() {

    private val _state : MutableStateFlow<ProfileScreenState> =
        MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()
}