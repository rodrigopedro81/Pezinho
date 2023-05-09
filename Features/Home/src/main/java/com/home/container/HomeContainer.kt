package com.home.container

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.designsystem.theme.PezinhoTheme
import com.home.container.bottomNavigation.BottomNavigationBar
import com.home.screens.home.HomeScreen
import navigation.Routes

@Composable
fun HomeContainer(
    navigateTo: (String) -> Unit,
    viewModel: HomeContainerViewModel = hiltViewModel()
) {
    val homeContainerState = viewModel.uiState.collectAsStateWithLifecycle()
    HomeContainerContent(
        navigateTo = navigateTo,
        homeContainerState = homeContainerState.value
    )
}

@Composable
fun HomeContainerContent(
    navigateTo: (String) -> Unit,
    homeContainerState: HomeContainerState
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) },
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
        HomeContainerContent(
            navigateTo = { navController.navigate(it) },
            homeContainerState = HomeContainerState()
        )
    }
}
