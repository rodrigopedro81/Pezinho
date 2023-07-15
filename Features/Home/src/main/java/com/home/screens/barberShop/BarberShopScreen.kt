package com.home.screens.barberShop

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.entities.AvailableService
import com.entities.BarberShop
import com.home.screens.barberList.getMockedBarber
import com.navigation.navigateToProfileScreen
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun BarberShopScreen(
    navController: NavController,
    selectedBarberShop: BarberShop?,
    viewModel: BarberShopScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(
        key1 = selectedBarberShop,
        block = {
            if (selectedBarberShop == null) {
                // TODO () -> Corrigir pra onde navega
                navController.navigateToProfileScreen()
            }
        }
    )
    selectedBarberShop?.let {
        val state = viewModel.state.collectAsStateWithLifecycle()
        BarberShopScreenContent(
            navController = navController,
            selectedBarberShop = it,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BarberShopScreenContent(
    navController: NavController,
    selectedBarberShop: BarberShop
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    SideEffect { coroutineScope.launch { modalSheetState.show() } }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val (image, bottomSheet) = createRefs()
        GlideImage(
            imageModel = { selectedBarberShop.wallpaper },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )
    }
}

@Composable
fun AvailableServiceItem(availableService: AvailableService) {
    Text(
        text = availableService.title,
        style = TextStyle(
            fontSize = 16.sp,
        ),
        modifier = Modifier.padding(16.dp),
    )
}

@Preview
@Composable
fun BarberShopScreenContentPreview() {
    BarberShopScreenContent(
        navController = rememberNavController(),
        selectedBarberShop = getMockedBarber()
    )
}
