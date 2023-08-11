package com.home.screens.barberShop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.entities.AvailableService
import com.entities.BarberShop
import com.home.screens.barberList.getMockedBarber
import com.navigation.Destinations

@Composable
fun BarberShopScreen(
    homeNavController: NavController,
    selectedBarberShop: BarberShop?,
    viewModel: BarberShopScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        if (selectedBarberShop == null) {
            homeNavController.navigate(Destinations.Main.barberListScreen.route)
        }
    }
    selectedBarberShop?.let {
        val state = viewModel.state.collectAsStateWithLifecycle()
        BarberShopScreenContent(
            homeNavController = homeNavController,
            selectedBarberShop = it,
        )
    }
}

@Composable
fun BarberShopScreenContent(
    homeNavController: NavController,
    selectedBarberShop: BarberShop,
) {
    LazyColumn(
        content = {
            items(selectedBarberShop.services) { availableService ->
                AvailableServiceItem(availableService = availableService)
            }
        }
    )
}

@Composable
fun AvailableServiceItem(availableService: AvailableService) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val (image, title) = createRefs()
        AsyncImage(model = availableService.title, contentDescription = availableService.title)
        Text(
            text = availableService.title,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end)
                }
        )
    }
}

@Preview
@Composable
fun BarberShopScreenContentPreview() {
    BarberShopScreenContent(
        homeNavController = rememberNavController(),
        selectedBarberShop = getMockedBarber(),
    )
}
