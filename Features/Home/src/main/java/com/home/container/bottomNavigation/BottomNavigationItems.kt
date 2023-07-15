package com.home.container.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.navigation.Directions

sealed class BottomNavigationItems(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val barColor: Color = Color.LightGray
) {
    object Home :
        BottomNavigationItems(
            Directions.HomeContainer.barberListScreen,
            "Home",
            Icons.Filled.Home
        )

    object Profile :
        BottomNavigationItems(
            Directions.HomeContainer.profileScreen,
            "Perfil",
            Icons.Filled.AccountCircle
        )
}