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
import com.maps.GPSClient
import com.pezinho.graph.MainNavGraph
import com.repositories.authentication.AuthenticationRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    @Inject lateinit var authenticationRepository: AuthenticationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PezinhoTheme {
                navController = rememberNavController()
                MainNavGraph(mainNavController = navController)
                RequestPermissions(
                    onResult = { areGranted ->
                        GPSClient.locationPermissionsGranted = areGranted
                        if (areGranted) {
                            if (authenticationRepository.userIsLoggedIn()) {
//                                navController.navigate(Routes.HomeContainer.destination)
                            }
                        }
                    }
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        GPSClient.removeCurrentLocationCallback()
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
