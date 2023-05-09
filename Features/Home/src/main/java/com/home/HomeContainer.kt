package com.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import navigation.Routes

@Composable
fun HomeContainer(navigateTo: (String) -> Unit) {
    HomeContainerContent(navigateTo)
}

@Composable
fun HomeContainerContent(
    navigateTo: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MainBottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            val containerNavController = rememberNavController()
            NavHost(
                navController = containerNavController,
                startDestination = Routes.Home.destination
            ) {

            }
        }
    }
}

@Composable
fun MainBottomBar() {
    BottomNavigation {
        BottomNavigationItem(
            selected =true,
            onClick = { },
            icon = { Text(text = "Icone") }
        )
    }
}

@Preview
@Composable
fun HomeContainerPreview() {
    val navController = rememberNavController()
    HomeContainerContent(navigateTo = { navController.navigate(it) })
}