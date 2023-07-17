package com.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.entities.BarberShop

fun NavController.navigateToOtherContainer(
    destination: Route,
    builder: NavOptionsBuilder.() -> Unit = {}
) = navigate(destination.container.replace(Args.START_DESTINATION_ARG, destination.route), builder)

fun <T> NavController.navigateWithSavedStateHandle(
    destination: Route,
    `object`: T,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    previousBackStackEntry?.savedStateHandle?.set(
        when (`object`) {
            is BarberShop -> SavedStateHandleArgs.BARBER_SHOP_KEY
            else -> throw IllegalArgumentException("Unknown object type, must map type in navigateWithSavedStateHandle")
        }, `object`
    )
    navigate(destination.route, builder)
}

object Args {

    internal const val START_DESTINATION_ARG = "startDestination"

    fun NavBackStackEntry.getStartDestination() = arguments?.getString(START_DESTINATION_ARG)

    fun startDestinationArg(direction: String? = null) = navArgument(START_DESTINATION_ARG) {
        type = NavType.StringType
        nullable = true
        defaultValue = direction
    }
}

// TODO () -> Envio do barber shop comprometido
// TODO () -> Implementar nova navegação por meio da nova lib
object SavedStateHandleArgs {

    const val BARBER_SHOP_KEY = "barberShop"

    private fun <T> NavController.getBackStackValue(key: String): T? {
        return previousBackStackEntry?.savedStateHandle?.get<T>(key)
    }

    fun NavController.getBarberShop(): BarberShop? = getBackStackValue(BARBER_SHOP_KEY)
}
