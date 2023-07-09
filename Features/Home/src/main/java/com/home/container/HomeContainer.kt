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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.designsystem.theme.PezinhoTheme
import com.home.container.bottomNavigation.BottomNavigationBar
import com.home.screens.home.HomeScreen
import navigation.Directions

@Composable
fun HomeContainer(
    mainNavController: NavHostController,
    viewModel: HomeContainerViewModel = hiltViewModel(),
    startDestination: String?
) {
    val homeContainerState = viewModel.uiState.collectAsStateWithLifecycle()
    HomeContainerContent(
        mainNavController = mainNavController,
        homeContainerState = homeContainerState.value,
        startDestination = startDestination
    )
}

@Composable
fun HomeContainerContent(
    mainNavController: NavHostController,
    homeContainerState: HomeContainerState,
    startDestination: String?
) {
    val homeContainerNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(homeContainerNavController) },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            NavHost(
                navController = homeContainerNavController,
                startDestination = startDestination ?: Directions.HomeContainer.homeScreen
            ) {
                composable(route = Directions.HomeContainer.homeScreen) {
                    HomeScreen()
                }
                composable(route = Directions.HomeContainer.profileScreen) {

                }
            }
        }
    }
}

@Preview
@Composable
fun HomeContainerPreview() {
    PezinhoTheme {
        val mainNavController = rememberNavController()
        HomeContainerContent(
            mainNavController = mainNavController,
            homeContainerState = HomeContainerState(),
            startDestination = Directions.HomeContainer.homeScreen
        )
    }
}
