package com.login.register

import androidx.lifecycle.ViewModel
import com.entities.AuthResult
import com.repositories.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val authenticator: Authenticator
) : ViewModel() {

    private val _state: MutableStateFlow<RegisterScreenState> = MutableStateFlow(
        RegisterScreenState()
    )
    val state: StateFlow<RegisterScreenState> = _state.asStateFlow()

    fun onClickEvent(
        clickEvent: RegisterEvent.ClickEvent
    ) {
        when (clickEvent) {
            RegisterEvent.ClickEvent.CLICK_BARBER -> updateIsClient(false)
            RegisterEvent.ClickEvent.CLICK_CLIENT -> updateIsClient(true)
        }
    }

    fun onTypeEvent(
        loginEvent: RegisterEvent.TypeEvent,
        value: String
    ) {
        when (loginEvent) {
            RegisterEvent.TypeEvent.UPDATE_PASSWORD -> updatePassword(value)
            RegisterEvent.TypeEvent.UPDATE_EMAIL -> updateEmail(value)
            RegisterEvent.TypeEvent.UPDATE_NAME -> updateName(value)
            RegisterEvent.TypeEvent.UPDATE_PHONE -> updatePhone(value)
            RegisterEvent.TypeEvent.UPDATE_ADDRESS -> updateAddress(value)
        }
    }

    fun onRegisterEvent(onResult: (AuthResult) -> Unit) {
        if (state.value.isFormValid) {
            updateIsLoading(true)
            authenticator.register(state.value.getUser()) { authResult ->
                updateIsLoading(false)
                onResult(authResult)
            }
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        _state.update { currentState ->
            return@update currentState.copy(isLoading = isLoading)
        }
    }

    private fun updateIsClient(isClient: Boolean) {
        _state.update { currentState ->
            return@update currentState.copy(isClient = isClient)
        }
    }

    private fun updateName(newName: String) {
        _state.update { currentState ->
            return@update currentState.copy(
                name = newName,
                isNameValid = checkName(newName)
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

    private fun updatePassword(newPassword: String) {
        _state.update { currentState ->
            return@update currentState.copy(
                password = newPassword,
                isPasswordValid = checkPassword(newPassword)
            )
        }
    }

    private fun updatePhone(newPhone: String) {
        _state.update { currentState ->
            return@update currentState.copy(
                phone = newPhone,
                isPhoneValid = checkPhone(newPhone)
            )
        }
    }

    private fun updateAddress(address: String) {
        _state.update { currentState ->
            return@update currentState.copy(
                address = address,
                isAddressValid = checkAddress(address)
            )
        }
    }

    // TODO () -> Criar checagens

    private fun checkName(newName: String): Boolean = true

    private fun checkEmail(newEmail: String): Boolean = true

    private fun checkPassword(newPassword: String): Boolean = true

    private fun checkPhone(newPhone: String): Boolean = true

    private fun checkAddress(address: String): Boolean = true
}