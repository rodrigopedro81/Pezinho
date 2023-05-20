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

open class MarkerInfo(
    val lat: Double,
    val lng: Double,
    val title: String,
    val snippet: String,
    val icon: Int
)