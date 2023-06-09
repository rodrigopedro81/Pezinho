package com.home.container.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import navigation.Directions

sealed class BottomNavigationItems(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home :
        BottomNavigationItems(Directions.HomeContainer.homeScreen, "Home", Icons.Filled.Home)
}