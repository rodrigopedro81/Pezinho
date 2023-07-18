package com.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.navigation.Args.START_DESTINATION_ARG
import com.navigation.Args.replaceArgs
import com.navigation.Args.withArgs

open class Route(
    val route: String,
    val container: String
) {
    val containerRoute
        get() = container.replaceArgs(START_DESTINATION_ARG to route)
}

sealed class Destinations(val container: String) {

    object Entry : Destinations("entry_container/".withArgs(START_DESTINATION_ARG)) {
        val splashScreen = Route("splash_screen", container)
    }

    object Login : Destinations("login_container/".withArgs(START_DESTINATION_ARG)) {
        val loginScreen = Route("login_screen", container)
        val registerScreen = Route("register_screen", container)
    }

    object Main : Destinations("home_container/".withArgs(START_DESTINATION_ARG)) {
        val barberListScreen = Route("barber_list_screen", container)
        val barberShopScreen = Route("barber_shop_screen", container)
        val profileScreen = Route("profile_screen", container)
    }
}

object Args {

    fun String.withArgs(vararg strings: String): String =
        strings.joinToString("", prefix = this, postfix = "") { "{$it}" }

    fun String.replaceArgs(vararg args: Pair<String, String>): String =
        args.fold(this) { acc, (key, value) ->
            acc.replace("{${key}}", value)
        }

    const val START_DESTINATION_ARG = "startDestination"

    fun NavBackStackEntry.getStartDestination() = arguments?.getString(START_DESTINATION_ARG)

    fun startDestinationArg(direction: String? = null) = navArgument(START_DESTINATION_ARG) {
        type = NavType.StringType
        nullable = true
        defaultValue = direction
    }
}