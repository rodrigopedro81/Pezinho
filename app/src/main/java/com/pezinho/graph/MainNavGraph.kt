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
import navigation.Args.START_DESTINATION_ARG
import navigation.Routes

@Composable
fun MainNavGraph(
    mainNavController: NavHostController
) {
    NavHost(
        navController = mainNavController,
        startDestination = Routes.ContainerRoutes.LOGIN_CONTAINER
    ) {
        composable(
            route = Routes.ContainerRoutes.LOGIN_CONTAINER,
            arguments = listOf(startDestinationArg())
        ) { backStackEntry ->
            LoginContainer(
                mainNavController = mainNavController,
                startDestination = backStackEntry.getStartDestination()
            )
        }
        composable(
            route = Routes.ContainerRoutes.HOME_CONTAINER,
            arguments = listOf(startDestinationArg())
        ) { backStackEntry ->
            HomeContainer(
                mainNavController = mainNavController,
                startDestination = backStackEntry.getStartDestination()
            )
        }
    }
}

fun startDestinationArg(defaultValue: String? = null) = navArgument(START_DESTINATION_ARG) {
    type = NavType.StringType
    nullable = true
    this.defaultValue = defaultValue
}

fun NavBackStackEntry.getStartDestination() = arguments?.getString(START_DESTINATION_ARG)