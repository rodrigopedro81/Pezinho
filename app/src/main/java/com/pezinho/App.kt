package com.pezinho

import NavigationRoutes
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.login.LoginScreen

@Composable
fun App(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Home.destination
    ){
        loginRoute(navController)
//        homeRoute(navController)
    }
}

fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    composable(route = NavigationRoutes.Login.destination) {
        LoginScreen(
            goToRegisterScreen = { navController.navigate(NavigationRoutes.Home.destination) }
        )
    }
    composable(route = NavigationRoutes.Register.destination) {

    }
}

//fun NavGraphBuilder.homeRoute(navController: NavHostController) {
//    composable(route = NavigationRoutes.Home.destination) {
//
//    }
//}