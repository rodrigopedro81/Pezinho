package com.pezinho.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.login.login.LoginScreen
import navigation.Destinations
import navigation.HomeRoutes
import navigation.LoginRoutes
import navigation.NavigationGraphs

// Navigation graph for Login Flow
fun NavGraphBuilder.loginGraph(navController: NavHostController) {
    navigation(startDestination = Destinations.LOGIN, route = NavigationGraphs.LOGIN) {
        composable(route = LoginRoutes.Login.destination) {
            LoginScreen(
                goToRegisterScreen = { navController.navigate(LoginRoutes.Register.destination) },
                goToHomeScreen = { navController.navigate(HomeRoutes.Home.destination) }
            )
        }
        composable(route = LoginRoutes.Register.destination) {
//            RegisterScreen()
        }
    }
}