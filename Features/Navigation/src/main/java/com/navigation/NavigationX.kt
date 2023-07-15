package com.navigation

import com.navigation.SavedStateHandleArgs.BARBER_SHOP_KEY
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.entities.BarberShop

// ENTRY CONTAINER

fun NavController.navigateToSplashScreen() {
    navigate(Directions.EntryContainer.splashScreen)
}

// HOME CONTAINER

fun NavController.navigateToBarberListScreen() {
    navigate(Directions.HomeContainer.barberListScreen)
}

fun NavController.navigateToProfileScreen() {
    navigate(Directions.HomeContainer.profileScreen)
}

fun NavController.navigateToBarberShopScreen(barberShop: BarberShop) {
    currentBackStackEntry?.savedStateHandle?.set(BARBER_SHOP_KEY, barberShop)
    navigate(Directions.HomeContainer.barberShopScreen)
}

// LOGIN CONTAINER

fun NavController.navigateToLoginScreen() {
    navigate(Directions.LoginContainer.loginScreen)
}

fun NavController.navigateToRegisterScreen() {
    navigate(Directions.LoginContainer.registerScreen)
}

object Args {

    internal const val START_DESTINATION_ARG = "{startDestination}"

    internal fun String.replaceStartDestination(route: String) = replace(START_DESTINATION_ARG, route)

    fun NavBackStackEntry.getStartDestination() = arguments?.getString(START_DESTINATION_ARG)

    fun startDestinationArg(direction: String? = null) = navArgument(START_DESTINATION_ARG) {
        type = NavType.StringType
        nullable = true
        defaultValue = direction
    }
}

object SavedStateHandleArgs {

    internal const val BARBER_SHOP_KEY = "barberShop"

    private fun <T> NavController.getBackStackValue(key: String): T? {
        return previousBackStackEntry?.savedStateHandle?.get<T>(key)
    }

    fun NavController.getBarberShop(): BarberShop? = getBackStackValue(BARBER_SHOP_KEY)
}
