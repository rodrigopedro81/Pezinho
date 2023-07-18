package com.entry.splashScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.designsystem.LoadingAnimation
import com.navigation.Destinations

@Composable
fun SplashScreen(
    mainNavController: NavHostController,
    entryContainerNavController: NavHostController
) {
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LoadingAnimation(
                duration = 2000L,
                onFinish = {
                    mainNavController.navigate(Destinations.Login.loginScreen.containerRoute)
                }
            )
        }
    }
}

