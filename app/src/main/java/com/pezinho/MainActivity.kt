package com.pezinho

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.designsystem.theme.PezinhoTheme
import com.maps.LocationProvider
import com.pezinho.graph.MainNavGraph
import dagger.hilt.android.AndroidEntryPoint
import navigation.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PezinhoTheme {
                navController = rememberNavController()
                MainNavGraph(navController = navController)
                RequestPermissions(
                    onResult = { areGranted ->
                        LocationProvider.locationPermissionsGranted = areGranted
                        if (areGranted) {
                            // Checar se usuário está logado
                            // Se estiver logado, navegar para HomeContainer
                            // Se não estiver logado, não fazer nada

                            // Para ir direto para Home
                            navController.navigate(Routes.HomeContainer.destination)
                        }
                    }
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        LocationProvider.removeCurrentLocationCallback()
    }

    @Composable
    fun RequestPermissions(onResult: (Boolean) -> Unit = {}) {
        val permissionRequester = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionsMap ->
            val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
            onResult.invoke(areGranted)
        }
        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        SideEffect {
            permissionRequester.launch(permissions)
        }
    }
}
