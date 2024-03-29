package com.entry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.entry.splashScreen.SplashScreen
import com.navigation.Destinations

@Composable
fun EntryContainer(
    mainNavController: NavHostController
) {
    EntryContainerContent(
        mainNavController = mainNavController
    )
}

@Composable
fun EntryContainerContent(
    mainNavController: NavHostController
) {
    val entryContainerNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            NavHost(
                navController = entryContainerNavController,
                startDestination = Destinations.Entry.splashScreen.route
            ) {
                composable(route =  Destinations.Entry.splashScreen.route) {
                    SplashScreen(
                        mainNavController,
                        entryContainerNavController
                    )
                }
            }
        }
    }
}
