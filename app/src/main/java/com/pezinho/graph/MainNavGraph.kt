package com.pezinho.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.entry.EntryContainer
import com.home.container.HomeContainer
import com.login.container.LoginContainer
import com.navigation.Args.getStartDestination
import com.navigation.Args.startDestinationArg
import com.navigation.Directions

@Composable
fun MainNavGraph(
    mainNavController: NavHostController
) {
    NavHost(
        navController = mainNavController,
        startDestination = Directions.HomeContainer.container
    ) {
        composable(
            route = Directions.EntryContainer.container
        ) {
            EntryContainer(
                mainNavController = mainNavController
            )
        }
        composable(
            route = Directions.LoginContainer.container,
            arguments = listOf(startDestinationArg())
        ) { backStackEntry ->
            LoginContainer(
                mainNavController = mainNavController,
                startDestination = backStackEntry.getStartDestination()
            )
        }
        composable(
            route = Directions.HomeContainer.container,
            arguments = listOf(startDestinationArg())
        ) { backStackEntry ->
            HomeContainer(
                mainNavController = mainNavController,
                startDestination = backStackEntry.getStartDestination()
            )
        }
    }
}
