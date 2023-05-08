package navigation

sealed class HomeRoutes(val destination: String) {
    object Home: HomeRoutes(Destinations.HOME)
}

