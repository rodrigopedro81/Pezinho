package com.pezinho

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pezinho.graph.homeGraph
import com.pezinho.graph.loginGraph
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
