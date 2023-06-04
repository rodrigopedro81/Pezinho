package com.repositories

import com.entities.AuthResult
import com.entities.User

interface Authenticator {

    fun login(
        email: String,
        password: String,
        onResult: (AuthResult) -> Unit
    )

    fun register(
        user: User,
        onResult: (AuthResult) -> Unit
    )

    fun userIsLoggedIn(): Boolean
}
