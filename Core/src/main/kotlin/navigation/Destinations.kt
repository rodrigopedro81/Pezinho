package navigation

object Destinations {
    const val LOGIN_GRAPH = "login_graph"
    const val LOGIN: String = "login"
    const val REGISTER: String= "register"
    const val HOME_CONTAINER = "home_container"
    const val HOME = "home"
    const val PROFILE = "profile"
}

sealed class Routes(val destination: String) {
    // Represents the whole login flow
    object LoginGraph: Routes(Destinations.LOGIN_GRAPH)
        // Represents the login screen
    object Login: Routes(Destinations.LOGIN)
        // Represents the register screen
    object Register: Routes(Destinations.REGISTER)

    // Represents the whole home flow
    object HomeContainer: Routes(Destinations.HOME_CONTAINER)
        // Represents the home screen
    object Home: Routes(Destinations.HOME)
        // Represents the profile screen
    object Profile: Routes(Destinations.PROFILE)
}
