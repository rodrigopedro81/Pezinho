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
import com.entities.BarberShop
import com.home.container.bottomNavigation.BottomNavigationBar
import com.home.screens.barberList.BarberListScreen
import com.home.screens.barberShop.BarberShopScreen
import com.home.screens.profile.ProfileScreen
import com.navigation.Destinations
import com.navigation.recoverObject

@Composable
fun HomeContainer(
    mainNavController: NavHostController,
    viewModel: HomeContainerViewModel = hiltViewModel(),
    startDestination: String?
) {
    val homeContainerState = viewModel.state.collectAsStateWithLifecycle()
    HomeContainerContent(
        mainNavController = mainNavController,
        homeContainerState = homeContainerState.value,
        startDestination = startDestination ?: Destinations.Main.barberListScreen.route
    )
}

@Composable
fun HomeContainerContent(
    mainNavController: NavHostController,
    homeContainerState: HomeContainerState,
    startDestination: String,
) {
    val homeContainerNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            BottomNavigationBar(homeContainerNavController)
//        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding),
            ) {
                NavHost(
                    navController = homeContainerNavController,
                    startDestination = startDestination
                ) {
                    composable(route = Destinations.Main.barberListScreen.route) {
                        BarberListScreen(navController = homeContainerNavController)
                    }
                    composable(route = Destinations.Main.barberShopScreen.route) {
                        val selectedBarberShop =
                            homeContainerNavController.recoverObject<BarberShop>()
                        BarberShopScreen(
                            homeNavController = homeContainerNavController,
                            selectedBarberShop = selectedBarberShop
                        )
                    }
                    composable(route = Destinations.Main.profileScreen.route) {
                        ProfileScreen()
                    }
                    composable(route = Destinations.Main.servicesScreen.route) {
//                      TODO () -> Construir tela de serviços feitos
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun HomeContainerPreview() {
    PezinhoTheme {
        val mainNavController = rememberNavController()
        HomeContainerContent(
            mainNavController = mainNavController,
            homeContainerState = HomeContainerState(),
            startDestination = Destinations.Main.barberListScreen.route
        )
    }
}
