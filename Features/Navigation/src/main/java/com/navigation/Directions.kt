package com.navigation

import com.navigation.Args.replaceStartDestination

/**
 *
 * All containers are represented by objects and all it's screens are represented by the variables.
 *
 */
sealed class Directions(val container: String) {

    object EntryContainer : Directions(Constants.Containers.ENTRY_CONTAINER) {

        val splashScreen
            get() = Constants.Containers.ENTRY_CONTAINER.replaceStartDestination(
                Constants.EntryScreens.SPLASH_SCREEN
            )
    }

    object LoginContainer : Directions(Constants.Containers.LOGIN_CONTAINER) {

        val loginScreen
            get() = Constants.Containers.LOGIN_CONTAINER.replaceStartDestination(
                Constants.LoginScreens.LOGIN_SCREEN
            )
        val registerScreen
            get() = Constants.Containers.LOGIN_CONTAINER.replaceStartDestination(
                Constants.LoginScreens.REGISTER_SCREEN
            )
    }

    object HomeContainer : Directions(Constants.Containers.HOME_CONTAINER) {

        val barberListScreen
            get() = Constants.Containers.HOME_CONTAINER.replaceStartDestination(
                Constants.HomeScreens.BARBER_LIST_SCREEN
            )

        val barberShopScreen
            get() = Constants.Containers.HOME_CONTAINER.replaceStartDestination(
                Constants.HomeScreens.BARBER_SHOP_SCREEN
            )

        val profileScreen
            get() = Constants.Containers.HOME_CONTAINER.replaceStartDestination(
                Constants.HomeScreens.PROFILE_SCREEN
            )
    }

    private object Constants {

        object Containers {
            const val ENTRY_CONTAINER = "entry_container/${Args.START_DESTINATION_ARG}"
            const val LOGIN_CONTAINER = "login_graph/${Args.START_DESTINATION_ARG}"
            const val HOME_CONTAINER = "home_container/${Args.START_DESTINATION_ARG}"
        }

        object EntryScreens {
            const val SPLASH_SCREEN = "splash"
        }

        object HomeScreens {
            const val PROFILE_SCREEN = "profile"
            const val BARBER_LIST_SCREEN = "barberList"
            const val BARBER_SHOP_SCREEN = "barberShop"
        }

        object LoginScreens {
            const val REGISTER_SCREEN = "register"
            const val LOGIN_SCREEN = "login"
        }
    }
}
