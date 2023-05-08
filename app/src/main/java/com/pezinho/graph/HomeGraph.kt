package com.pezinho.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import navigation.HomeRoutes
import navigation.NavigationGraphs


fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(startDestination = HomeRoutes.Home.destination, route = NavigationGraphs.HOME) {
        composable(route = HomeRoutes.Home.destination) {
//        HomeScreen()
        }
    }
}