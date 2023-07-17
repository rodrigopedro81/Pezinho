package com.home.container.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.designsystem.WhiteBoard
import com.designsystem.theme.PezinhoTheme
import com.navigation.Destinations

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationItems.Home,
        BottomNavigationItems.Profile
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = backStackEntry?.destination?.route
    val initialBarColor = Color.LightGray
    val backgroundColor = remember { mutableStateOf(initialBarColor) }
    BottomNavigation(backgroundColor = backgroundColor.value) {
        Items(
            updateBarColor = { color ->
                backgroundColor.value = color
            },
            navController = navController
        )
//        }
    }
}

@Composable
fun RowScope.Items(
    updateBarColor: (Color) -> Unit,
    navController: NavHostController
) {
    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Profile
    )
    for (item in items) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(item.selectedColor, RoundedCornerShape(62.dp))
                .selectableGroup()
                .clickable {
                    navController.navigate(item.route)
                },
        ) {
            Text(text = item.label, style = TextStyle(color = Color.White))
        }
    }
}

sealed class NavigationItems(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val selectedColor: Color,
    val barColor: Color,

    ) {
    object Home :
        NavigationItems(
            route = Destinations.Main.barberListScreen.route,
            label = "Home",
            icon = Icons.Filled.Home,
            selectedColor = Color.DarkGray,
            barColor = Color.LightGray
        )

    object Profile :
        NavigationItems(
            route = Destinations.Main.profileScreen.route,
            label = "Perfil",
            icon = Icons.Filled.AccountCircle,
            selectedColor = Color.DarkGray,
            barColor = Color.LightGray
        )
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