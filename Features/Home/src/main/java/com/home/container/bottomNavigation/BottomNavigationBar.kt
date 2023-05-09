package com.home.container.bottomNavigation

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.designsystem.WhiteBoard
import com.designsystem.theme.PezinhoTheme

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationItems.Home
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
            BottomNavigationBar(navController = rememberNavController())
        }
    }
}