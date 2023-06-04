package com.entities

data class AuthResult(
    val success: Boolean = false,
    val error: Exception? = null
)
