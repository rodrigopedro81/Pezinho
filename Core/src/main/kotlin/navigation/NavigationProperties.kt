package navigation

import navigation.Args.START_DESTINATION_ARG

object Args {
    const val START_DESTINATION_ARG = "{startDestination}"
}

object Routes {

    object ContainerRoutes {

        const val HOME_CONTAINER = "home_container/$START_DESTINATION_ARG"
        const val LOGIN_CONTAINER = "login_graph/$START_DESTINATION_ARG"
    }

    object LoginContainerRoutes {

        const val LOGIN: String = "login"
        const val REGISTER: String = "register"
    }

    object HomeContainerRoutes {

        const val HOME = "home"
        const val PROFILE = "profile"
    }
}

sealed class Directions {

    object LoginGraph : Directions() {

        val loginScreen
            get() = Routes.LoginContainerRoutes.LOGIN
        val registerScreen
            get() = Routes.LoginContainerRoutes.REGISTER
    }

    object HomeContainer : Directions() {

        val homeScreen
            get() = Routes.ContainerRoutes.HOME_CONTAINER.replace(
                START_DESTINATION_ARG,
                Routes.HomeContainerRoutes.HOME
            )
        val profileScreen
            get() = Routes.ContainerRoutes.HOME_CONTAINER.replace(
                START_DESTINATION_ARG,
                Routes.HomeContainerRoutes.PROFILE
            )
    }
}
