package com.pezinho.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.home.container.HomeContainer
import navigation.Routes

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LoginGraph.destination
    ) {
        // Represents the whole login flow
        loginGraph(
            route = Routes.LoginGraph.destination,
            navController = navController
        )
        // Represents the whole home flow
        composable(
            route = Routes.HomeContainer.destination
        ) {
            HomeContainer(
                navigateTo = { destination -> navController.navigate(destination) }
            )
        }
    }
}
