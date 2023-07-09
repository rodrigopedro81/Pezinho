package com.pezinho.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.home.container.HomeContainer
import com.login.container.LoginContainer
import com.entry.EntryContainer
import navigation.Directions
import navigation.Directions.Args.START_DESTINATION_ARG

@Composable
fun MainNavGraph(
    mainNavController: NavHostController
) {
    NavHost(
        navController = mainNavController,
        startDestination = Directions.EntryContainer.container
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

fun startDestinationArg(direction: String? = null) = navArgument(START_DESTINATION_ARG) {
    type = NavType.StringType
    nullable = true
    defaultValue = direction
}

fun NavBackStackEntry.getStartDestination() = arguments?.getString(START_DESTINATION_ARG)