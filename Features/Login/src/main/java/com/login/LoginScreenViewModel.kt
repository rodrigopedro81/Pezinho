package com.login

import androidx.lifecycle.ViewModel
import com.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LoginScreenState(
    val email: String = "",
    val password: String = ""
)

enum class LoginScreenEvent {
    UPDATE_PASSWORD,
    UPDATE_EMAIL,
    CLICK_LOGIN,
    CLICK_REGISTER
}

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState())
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()

    fun <T> onEvent(
        event: LoginScreenEvent,
        value: T? = null
    ) {
        when(event) {
            LoginScreenEvent.UPDATE_PASSWORD -> updatePassword(value as String)
            LoginScreenEvent.UPDATE_EMAIL -> updateEmail(value as String)
            LoginScreenEvent.CLICK_LOGIN -> login()
            LoginScreenEvent.CLICK_REGISTER -> register()
        }
    }

    private fun updatePassword(newPassword: String) {
        _uiState.update { currentState ->
            return@update currentState.copy(password = newPassword)
        }
    }

    private fun updateEmail(newEmail: String) {
        _uiState.update { currentState ->
            return@update currentState.copy(email = newEmail)
        }
    }

    private fun login() {
        loginRepository.login(uiState.value.email, uiState.value.password)
    }

    private fun register() {
        loginRepository.register(uiState.value.email, uiState.value.password)
    }
}
