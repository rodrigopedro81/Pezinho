package com.login.container

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
import com.login.screens.login.LoginScreen
import com.login.screens.register.RegisterScreen
import com.navigation.Destinations

@Composable
fun LoginContainer(
    mainNavController: NavHostController,
    startDestination: String?,
) {
    LoginContainerContent(
        mainNavController = mainNavController,
        startDestination ?: Destinations.Login.loginScreen.route
    )
}

@Composable
fun LoginContainerContent(
    mainNavController: NavHostController,
    startDestination: String,
) {
    val loginContainerNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            NavHost(
                navController = loginContainerNavController,
                startDestination = startDestination
            ) {
                composable(route = Destinations.Login.loginScreen.route) {
                    LoginScreen(
                        mainNavController = mainNavController,
                        loginContainerNavController = loginContainerNavController
                    )
                }
                composable(route = Destinations.Login.registerScreen.route) {
                    RegisterScreen(
                        mainNavController = mainNavController,
                        loginContainerNavController = loginContainerNavController
                    )
                }
            }
        }
    }
}
