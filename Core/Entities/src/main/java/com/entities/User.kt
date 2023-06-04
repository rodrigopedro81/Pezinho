package com.entities

data class User(
    val id: String = "",
    val name: String,
    val email: String,
    val userType: UserType,
    val address: String = "",
    val password: String = ""
)

enum class UserType{
    BARBER,
    CLIENT
}

class MarkerInfo(
    val lat: Double,
    val lng: Double,
    val title: String,
    val snippet: String,
    val icon: Int
)