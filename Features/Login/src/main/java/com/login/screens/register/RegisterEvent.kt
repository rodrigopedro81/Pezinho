package com.login.screens.register

class RegisterEvent {
    enum class TypeEvent {
        UPDATE_PASSWORD,
        UPDATE_EMAIL,
        UPDATE_NAME,
        UPDATE_PHONE,
        UPDATE_ADDRESS,
        SELECT_ADDRESS
    }

    enum class ClickEvent {
        CLICK_BARBER,
        CLICK_CLIENT
    }
}