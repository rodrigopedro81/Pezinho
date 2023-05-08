package navigation

sealed class LoginRoutes(val destination: String) {
    object Login: LoginRoutes(Destinations.LOGIN)
    object Register: LoginRoutes(Destinations.REGISTER)
}
