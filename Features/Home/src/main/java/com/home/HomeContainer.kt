package com.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.designsystem.theme.PezinhoTheme
import com.home.home.HomeScreen
import navigation.Routes

@Composable
fun HomeContainer(navigateTo: (String) -> Unit) {
    HomeContainerContent(navigateTo)
}

@Composable
fun HomeContainerContent(
    navigateTo: (String) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MainBottomNavigation(navController) },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            NavHost(
                navController = navController,
                startDestination = Routes.Home.destination
            ) {
                composable(route = Routes.Home.destination) {
                    HomeScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeContainerPreview() {
    PezinhoTheme {
        val navController = rememberNavController()
        HomeContainerContent(navigateTo = { navController.navigate(it) })
    }
}
