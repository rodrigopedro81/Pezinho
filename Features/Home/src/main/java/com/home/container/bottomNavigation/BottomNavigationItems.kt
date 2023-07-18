package com.home.container.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.navigation.Destinations
import com.navigation.Route

sealed class BottomNavigationItems(
    val route: Route,
    val label: String,
    val icon: ImageVector,
    val selectedColor: Color,
    val barColor: Color,

    ) {
    object Home :
        BottomNavigationItems(
            route = Destinations.Main.barberListScreen,
            label = "Home",
            icon = Icons.Filled.Home,
            selectedColor = Color.DarkGray,
            barColor = Color.LightGray
        )

    object Profile :
        BottomNavigationItems(
            route = Destinations.Main.profileScreen,
            label = "Perfil",
            icon = Icons.Filled.AccountCircle,
            selectedColor = Color.DarkGray,
            barColor = Color.LightGray
        )
}