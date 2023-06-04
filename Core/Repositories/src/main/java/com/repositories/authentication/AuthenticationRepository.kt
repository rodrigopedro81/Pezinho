package com.repositories.authentication

import com.entities.AuthResult
import com.entities.Barber
import com.entities.Client
import com.entities.User

interface AuthenticationRepository {

    fun login(
        email: String,
        password: String,
        onResult: (AuthResult) -> Unit
    )

    fun registerClient(client: Client, password: String, onResult: (AuthResult) -> Unit)

    fun registerBarber(barber: Barber, password: String, onResult: (AuthResult) -> Unit)

    fun userIsLoggedIn(): Boolean
}
