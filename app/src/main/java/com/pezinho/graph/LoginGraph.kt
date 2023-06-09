package com.pezinho.graph

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
import com.login.login.LoginScreen
import com.login.register.RegisterScreen
import navigation.Directions
import navigation.Routes

@Composable
fun LoginContainer(
    mainNavController: NavHostController,
    startDestination: String?
) {
    LoginContainerContent(
        mainNavController = mainNavController,
        startDestination = startDestination
    )
}

@Composable
fun LoginContainerContent(
    mainNavController: NavHostController,
    startDestination: String?
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
                startDestination = startDestination ?: Routes.HomeContainerRoutes.HOME
            ) {
                composable(route = Directions.LoginGraph.loginScreen) {
                    LoginScreen(
                        mainNavController = mainNavController,
                        loginContainerNavController = loginContainerNavController
                    )
                }
                composable(route = Directions.LoginGraph.registerScreen) {
                    RegisterScreen(
                        mainNavController = mainNavController,
                        loginContainerNavController = loginContainerNavController
                    )
                }
            }
        }
    }
}
