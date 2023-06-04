package com.login.register

import androidx.compose.runtime.Stable
import com.entities.User
import com.entities.UserType

@Stable
data class RegisterScreenState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val address: String = "",
    val isAddressValid: Boolean = false,
    val isNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isPhoneValid: Boolean = false,
    val isLoading: Boolean = false,
    val isClient: Boolean? = null,
) {
    val isFormValid: Boolean
        get() = if (isClient != null && isClient) {
            isBaseFormValid
        } else {
            isBaseFormValid && isAddressValid
        }

    val isBaseFormValid: Boolean
        get() = isNameValid && isEmailValid && isPasswordValid && isPhoneValid

    fun getUser(): User = User(
        name = name,
        email = email,
        userType = if (isClient == true) UserType.CLIENT else UserType.BARBER,
        address = address,
        password = password,
    )
}