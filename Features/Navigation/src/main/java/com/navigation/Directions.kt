package com.navigation

open class Route(val route: String, val container: String)

sealed class Destinations(val container: String) {

    object Entry : Destinations("entry_container/{${Args.START_DESTINATION_ARG}}") {
        val splashScreen = Route("splash_screen", container)
    }

    object Login : Destinations("login_container/{${Args.START_DESTINATION_ARG}}") {
        val loginScreen = Route("login_screen", container)
        val registerScreen = Route("register_screen", container)
    }

    object Main : Destinations("home_container/{${Args.START_DESTINATION_ARG}}") {
        val barberListScreen = Route("barber_list_screen", container)
        val barberShopScreen = Route("barber_shop_screen", container)
        val profileScreen = Route("profile_screen", container)
    }
}
