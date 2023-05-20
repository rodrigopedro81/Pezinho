package com.login.login

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

class LoginEvent {
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
    // TODO () -> Pode testar aqui nessa view model, basta incluir no construtor o repository novo
    // Que o sistema de injeção de dependências do Hilt vai se encarregar de injetar
    // E aí você já consegue ver o retorno da chamada.
    // Pode até botar a chamada de teste num bloco init pra não ter de linkar a nada da ui
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginScreenState: MutableStateFlow<LoginScreenState> = MutableStateFlow(
        LoginScreenState()
    )
    val loginScreenState: StateFlow<LoginScreenState> = _loginScreenState.asStateFlow()

    fun onClickEvent(
        loginEvent: LoginEvent.ClickEvent
    ) {
        when(loginEvent) {
            LoginEvent.ClickEvent.CLICK_LOGIN -> login()
            LoginEvent.ClickEvent.CLICK_REGISTER -> register()
        }
    }

    fun onTypeEvent(
        loginEvent: LoginEvent.TypeEvent,
        value: String
    ) {
        when(loginEvent) {
            LoginEvent.TypeEvent.UPDATE_PASSWORD -> updatePassword(value)
            LoginEvent.TypeEvent.UPDATE_EMAIL -> updateEmail(value)
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
