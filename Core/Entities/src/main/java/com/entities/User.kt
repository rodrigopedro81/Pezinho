package com.entities

open class User(
    val name: String = "",
    val email: String = "",
    val userType: UserType = UserType.CLIENT,
    val phone: String = "",
) {

    companion object {

        fun getUser(barber: Barber) = User(
            name = barber.name,
            email = barber.email,
            userType = UserType.BARBER,
            phone = barber.phone,
        )

//        fun getUser(client: Client) = User(
//            name = barber.name,
//            email = barber.email,
//            userType = UserType.BARBER,
//            phone = barber.phone,
//        )
    }
}

class Barber(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val title: String = "",
    val snippet: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

class Client(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val lastLat: Double = 0.0,
    val lastLng: Double = 0.0,
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