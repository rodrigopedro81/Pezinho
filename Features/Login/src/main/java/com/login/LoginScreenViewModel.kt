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

class Event {
    enum class TypeEvent {
        UPDATE_PASSWORD,
        UPDATE_EMAIL,
    }
    enum class ClickEvent {
        CLICK_LOGIN,
        CLICK_REGISTER
    }
}

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginScreenState: MutableStateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState())
    val loginScreenState: StateFlow<LoginScreenState> = _loginScreenState.asStateFlow()

    fun onClickEvent(
        event: Event.ClickEvent
    ) {
        when(event) {
            Event.ClickEvent.CLICK_LOGIN -> login()
            Event.ClickEvent.CLICK_REGISTER -> register()
        }
    }

    fun onTypeEvent(
        event: Event.TypeEvent,
        value: String
    ) {
        when(event) {
            Event.TypeEvent.UPDATE_PASSWORD -> updatePassword(value)
            Event.TypeEvent.UPDATE_EMAIL -> updateEmail(value)
        }
    }

    private fun updatePassword(newPassword: String) {
        _loginScreenState.update { currentState ->
            return@update currentState.copy(password = newPassword)
        }
    }

    private fun updateEmail(newEmail: String) {
        _loginScreenState.update { currentState ->
            return@update currentState.copy(email = newEmail)
        }
    }

    private fun login() {
        loginRepository.login(loginScreenState.value.email, loginScreenState.value.password)
    }

    private fun register() {
        loginRepository.register(loginScreenState.value.email, loginScreenState.value.password)
    }
}
