package com.login.register

class RegisterEvent {
    enum class TypeEvent {
        UPDATE_PASSWORD,
        UPDATE_EMAIL,
        UPDATE_NAME,
        UPDATE_PHONE,
        UPDATE_ADDRESS
    }

    enum class AuthEvent {
        CLICK_REGISTER
    }

    enum class ClickEvent {
        CLICK_BARBER,
        CLICK_CLIENT
    }
}