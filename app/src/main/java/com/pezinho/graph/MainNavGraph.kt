package com.pezinho.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.entry.EntryContainer
import com.home.container.HomeContainer
import com.login.container.LoginContainer
import com.navigation.Args
import com.navigation.Destinations
import com.navigation.getArgument
import com.navigation.navArguments

@Composable
fun MainNavGraph(
    mainNavController: NavHostController
) {
    NavHost(
        navController = mainNavController,
        startDestination = Destinations.Entry.container
    ) {
        composable(
            route = Destinations.Entry.container
        ) {
            EntryContainer(
                mainNavController = mainNavController
            )
        }
        composable(
            route = Destinations.Login.container,
            arguments = Destinations.Login.container.navArguments()
        ) { backStackEntry ->
            LoginContainer(
                mainNavController = mainNavController,
                startDestination = backStackEntry.getArgument(Args.StartDestination)
            )
        }
        composable(
            route = Destinations.Main.container,
            arguments = Destinations.Main.container.navArguments()
        ) { backStackEntry ->
            HomeContainer(
                mainNavController = mainNavController,
                startDestination = backStackEntry.getArgument(Args.StartDestination)
            )
        }
    }
}
