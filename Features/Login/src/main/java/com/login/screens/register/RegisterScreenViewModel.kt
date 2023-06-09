package com.login.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entities.AuthResult
import com.entities.Barber
import com.entities.Client
import com.entities.UserType.BARBER
import com.entities.UserType.CLIENT
import com.entities.UserType.UNKNOWN
import com.repositories.authentication.AuthenticationRepository
import com.repositories.network.GeoCodingRepository
import commons.checkAddress
import commons.checkEmail
import commons.checkName
import commons.checkPassword
import commons.checkPhone
import commons.shouldSearchCompletions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val geoCodingRepository: GeoCodingRepository,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _state: MutableStateFlow<RegisterScreenState> = MutableStateFlow(
        RegisterScreenState()
    )
    val state: StateFlow<RegisterScreenState> = _state.asStateFlow()
    private var job: Job? = null

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
            RegisterEvent.TypeEvent.UPDATE_NAME -> updateName(value)
            RegisterEvent.TypeEvent.UPDATE_EMAIL -> updateEmail(value)
            RegisterEvent.TypeEvent.UPDATE_PASSWORD -> updatePassword(value)
            RegisterEvent.TypeEvent.UPDATE_PHONE -> updatePhone(value)
            RegisterEvent.TypeEvent.UPDATE_ADDRESS -> searchPlaces(value)
            RegisterEvent.TypeEvent.SELECT_ADDRESS -> selectAutoComplete(value)
        }
    }

    private fun searchPlaces(search: String) {
        updateAddress(search)
        job?.cancel()
        clearAutoCompletePredictions()
        if (shouldSearchCompletions(search)) {
            job = viewModelScope.launch {
                delay(1000)
                val autoCompleteList = geoCodingRepository.getAutoCompletes(search)
                _state.update {
                    it.copy(autoCompletePredictions = autoCompleteList)
                }
            }
        }
    }

    private fun selectAutoComplete(selectedAddress: String) {
        val selectedAutoComplete = _state.value.autoCompletePredictions.first {
            it.formattedAddress == selectedAddress
        }
        _state.update { currentState ->
            return@update currentState.copy(
                latitude = selectedAutoComplete.latitude,
                longitude = selectedAutoComplete.longitude,
                isAddressValid = true,
                address = selectedAddress
            ).also {
                clearAutoCompletePredictions()
            }
        }
    }

    private fun clearAutoCompletePredictions() {
        _state.update {
            it.copy(autoCompletePredictions = emptyList())
        }
    }

    fun onRegisterEvent(
        onResult: (AuthResult) -> Unit
    ) {
        updateIsLoading(true)
        with(state.value) {
            if (isFormValid) {
                when (userType) {
                    CLIENT -> registerClient(onResult)
                    BARBER -> registerBarber(onResult)
                    UNKNOWN -> throw IllegalStateException("User type is not valid")
                }
            }
        }
    }

    private fun registerBarber(onResult: (AuthResult) -> Unit) {
//        authenticationRepository.registerBarber(
//            getBarber(),
//            state.value.password
//        ) { authResult ->
//            updateIsLoading(false)
//            onResult(authResult)
//        }
        onResult.invoke(AuthResult(true))
    }

    private fun registerClient(onResult: (AuthResult) -> Unit) {
//        authenticationRepository.registerClient(
//            getClient(),
//            state.value.password
//        ) { authResult ->
//            updateIsLoading(false)
//            onResult(authResult)
//        }
        onResult.invoke(AuthResult(true))
    }

    private fun getClient(): Client = Client(
        name = state.value.name,
        email = state.value.email,
        phone = state.value.phone
    )

    private fun getBarber(): Barber = Barber(
        name = state.value.name,
        email = state.value.email,
        phone = state.value.phone,
        address = state.value.address,
        latitude = state.value.latitude,
        longitude = state.value.longitude
    )

    private fun updateIsLoading(isLoading: Boolean) {
        _state.update { currentState ->
            return@update currentState.copy(isLoading = isLoading)
        }
    }

    private fun updateIsClient(isClient: Boolean) {
        _state.update { currentState ->
            return@update currentState.copy(userType = if (isClient) CLIENT else BARBER)
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
}