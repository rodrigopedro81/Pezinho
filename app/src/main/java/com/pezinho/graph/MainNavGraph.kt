package com.pezinho.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import navigation.NavigationGraphs

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationGraphs.LOGIN
    ) {
        loginGraph(navController)
        homeGraph(navController)
        // All other navigation graphs come here
    }
}
