
sealed class NavigationRoutes(val destination: String) {
    object Register: NavigationRoutes(Destinations.REGISTER)
    object Home: NavigationRoutes(Destinations.HOME)
    object Login: NavigationRoutes(Destinations.LOGIN)
}
