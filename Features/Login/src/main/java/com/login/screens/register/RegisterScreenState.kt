package com.login.screens.register

import androidx.compose.runtime.Stable
import com.entities.AutoComplete
import com.entities.UserType

@Stable
data class RegisterScreenState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val address: String = "",
    val isAddressValid: Boolean = false,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val isNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isPhoneValid: Boolean = false,
    val isLoading: Boolean = false,
    val userType: UserType = UserType.UNKNOWN,
    val autoCompletePredictions: List<AutoComplete> = emptyList()
) {
    val isFormValid: Boolean
        get() = if (userType == UserType.CLIENT) {
            isBaseFormValid
        } else {
            isBaseFormValid && isAddressValid
        }

    private val isBaseFormValid: Boolean
        get() = isNameValid && isEmailValid && isPasswordValid && isPhoneValid
}