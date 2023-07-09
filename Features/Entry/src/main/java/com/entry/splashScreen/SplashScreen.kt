package com.entry.splashScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.designsystem.LoadingAnimation
import navigation.Directions


@Composable
fun SplashScreen(mainNavController: NavHostController) {
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LoadingAnimation(
                duration = 2000L,
                onFinish = {
                    mainNavController.navigate(Directions.LoginContainer.loginScreen)
                }
            )
        }
    }
}

