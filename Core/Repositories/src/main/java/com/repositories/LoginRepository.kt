package com.repositories

interface LoginRepository {
    fun login(email: String, password: String)
    fun register(email: String, password: String)
}
