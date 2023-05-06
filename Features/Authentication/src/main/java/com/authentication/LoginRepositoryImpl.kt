package com.authentication

import com.network.service.LoginService
import com.repositories.LoginRepository

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {

    override fun login(email: String, password: String) {
        loginService
    }

    override fun register(email: String, password: String) {
        loginService
    }
}