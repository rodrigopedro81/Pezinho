package com.pezinho

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.designsystem.theme.PezinhoTheme
import com.pezinho.graph.MainNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PezinhoTheme {
                navController = rememberNavController()
                MainNavGraph(mainNavController = navController)
            }
        }
    }

    data class LocationDetails(val latitude: Double, val longitude: Double)
}

fun ComponentActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
