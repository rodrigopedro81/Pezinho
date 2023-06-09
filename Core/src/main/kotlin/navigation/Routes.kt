package navigation


/**
 * All routes are stored in this object
 *
 * Container routes keeps track of all the routes for the main containers, these are simple
 * constants with the arguments constants
 *
 * The other objects holds their respective routes
 *
 * Example:
 *
 *   LoginContainerRoutes holds the routes for the screens that are inside the LoginContainer
 *
 *      - Route for Login Screen
 *
 *      - Route for Register Screen
 *
 *      - And any other screens that come to exist in this container
 *
 * @see Directions
 * @see Args
 */
object Routes {

    object ContainerRoutes {

        const val HOME_CONTAINER = "home_container/${Args.START_DESTINATION_ARG}"
        const val LOGIN_CONTAINER = "login_graph/${Args.START_DESTINATION_ARG}"
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