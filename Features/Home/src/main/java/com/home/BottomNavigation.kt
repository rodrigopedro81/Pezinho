package com.home

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.designsystem.WhiteBoard
import com.designsystem.theme.PezinhoTheme
import navigation.Routes

sealed class BottomNavigationScreens(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home :
        BottomNavigationScreens(Routes.Home.destination, "Home", Icons.Filled.Home)
}

@Composable
fun MainBottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavigationScreens.Home,
    )
    val currentRoute = navController.currentDestination?.route
    BottomNavigation {
        for (item in items) {
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != null && currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                },
                icon = { Image(item.icon, null) },
                label = { Text(text = item.label) }
            )
        }
    }
}

@Preview
@Composable
fun MainBottomBarPreview() {
    PezinhoTheme {
        WhiteBoard {
            MainBottomNavigation(navController = rememberNavController())
        }
    }
}