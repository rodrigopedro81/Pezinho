package com.home

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
        BottomNavigationScreens.Home
    )
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        items.forEach { item ->
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