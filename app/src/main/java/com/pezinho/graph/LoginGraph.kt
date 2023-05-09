package com.pezinho.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.login.login.LoginScreen
import com.login.register.RegisterScreen
import navigation.Destinations
import navigation.Routes

fun NavGraphBuilder.loginGraph(route: String, navController: NavHostController) {
    navigation(startDestination = Destinations.LOGIN, route = route) {
        composable(route = Routes.Login.destination) {
            LoginScreen(
                navigateTo = { destination -> navController.navigate(destination) }
            )
        }
        composable(route = Routes.Register.destination) {
            RegisterScreen(
                navigateTo = { destination -> navController.navigate(destination) }
            )
        }
    }
}