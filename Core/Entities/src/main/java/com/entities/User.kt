package com.entities

import java.io.Serializable

abstract class User() {
    abstract val id: Long
    abstract val name: String
    abstract val email: String
    abstract val phone: String
}

class Barber(
    override val id: Long,
    override val name: String = "",
    override val email: String = "",
    override val phone: String = "",
) : User()

class Client(
    override val id: Long,
    override val name: String = "",
    override val email: String = "",
    override val phone: String = "",
) : User()

data class BarberShop(
    val wallpaper: String = "",
    val icon: String = "",
    val address: String = "",
    val title: String = "",
    val snippet: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val barbers: List<Barber> = listOf(),
    val services: List<AvailableService> = listOf()
): Serializable

class AvailableService(
    val id: Long,
    val title: String,
    val description: String,
    val price: String,
    val duration: String
)

enum class UserType {
    BARBER,
    CLIENT,
    UNKNOWN
}

class MarkerInfo(
    val lat: Double,
    val lng: Double,
    val title: String,
    val snippet: String = "",
    val icon: Int = 0
)