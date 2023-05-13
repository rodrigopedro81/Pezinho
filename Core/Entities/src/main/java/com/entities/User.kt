package com.entities

data class User(
    val name: String,
    val email: String,
    val userType: UserType,
    val address: String,
    val id: String
)

enum class UserType{
    BARBER,
    CLIENT
}
