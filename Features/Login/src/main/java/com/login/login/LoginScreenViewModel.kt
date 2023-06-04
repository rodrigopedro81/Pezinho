package com.login.login

import androidx.lifecycle.ViewModel
import com.entities.AuthResult
import com.repositories.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLoading: Boolean = false
)

class LoginEvent {
    enum class TypeEvent {
        UPDATE_PASSWORD,
        UPDATE_EMAIL,
    }

    enum class AuthEvent {
        CLICK_LOGIN
    }
}

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authenticator: Authenticator
) : ViewModel() {

    private val _state: MutableStateFlow<LoginScreenState> = MutableStateFlow(
        LoginScreenState()
    )
    val state: StateFlow<LoginScreenState> = _state.asStateFlow()

    fun onAuthEvent(
        loginEvent: LoginEvent.AuthEvent,
        onResult: (AuthResult) -> Unit,
    ) {
        when (loginEvent) {
            LoginEvent.AuthEvent.CLICK_LOGIN -> login(onResult)
        }
    }

    fun onTypeEvent(
        loginEvent: LoginEvent.TypeEvent,
        value: String
    ) {
        when (loginEvent) {
            LoginEvent.TypeEvent.UPDATE_PASSWORD -> updatePassword(value)
            LoginEvent.TypeEvent.UPDATE_EMAIL -> updateEmail(value)
        }
    }

    private fun updatePassword(newPassword: String) {
        _state.update { currentState ->
            return@update currentState.copy(
                password = newPassword,
                isPasswordValid = checkPassword(newPassword)
            )
        }
    }

    private fun updateEmail(newEmail: String) {
        _state.update { currentState ->
            return@update currentState.copy(
                email = newEmail,
                isEmailValid = checkEmail(newEmail)
            )
        }
    }

    private fun login(onResult: (AuthResult) -> Unit) {
        if (dataIsValid()) {
            authenticator.login(
                state.value.email,
                state.value.password,
                onResult
            )
        }
    }

    private fun checkEmail(newEmail: String): Boolean {
        return true
        // TODO () -> Checar validade do email
    }

    private fun checkPassword(newPassword: String): Boolean {
        return true
        // TODO () -> Checar validade da senha
    }

    private fun dataIsValid(): Boolean =
        state.value.isEmailValid && state.value.isPasswordValid
}
