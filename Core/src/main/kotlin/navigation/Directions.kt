package navigation

import navigation.Args.replaceStartDestination

/**
 * Use this class to navigate to any main container's screen.
 *
 * All containers are represented by objects and all it's screens are represented by the variables.
 *
 * Examples:
 *
 *    * mainNavController.navigate(Directions.LoginContainer.loginScreen)
 *    * mainNavController.navigate(Directions.HomeContainer.loginScreen)
 *
 * @see Routes
 * @see Args
 */
sealed class Directions {

    object LoginContainer : Directions() {

        val loginScreen
            get() = Routes.ContainerRoutes.LOGIN_CONTAINER.replaceStartDestination(
                Routes.LoginContainerRoutes.LOGIN
            )
        val registerScreen
            get() = Routes.ContainerRoutes.LOGIN_CONTAINER.replaceStartDestination(
                Routes.LoginContainerRoutes.REGISTER
            )
    }

    object HomeContainer : Directions() {

        val homeScreen
            get() = Routes.ContainerRoutes.HOME_CONTAINER.replaceStartDestination(
                Routes.HomeContainerRoutes.HOME
            )
        val profileScreen
            get() = Routes.ContainerRoutes.HOME_CONTAINER.replaceStartDestination(
                Routes.HomeContainerRoutes.PROFILE
            )
    }
}
