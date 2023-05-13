package com.home.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.entities.Barber
import com.google.android.gms.maps.model.LatLng
import com.maps.GoogleMaps
import com.maps.LocationProvider

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state.value,
        currentLocation = LocationProvider.getCurrentLocation()
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    state: HomeState,
    currentLocation: LatLng
) {
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Expanded,
        confirmStateChange = { false }
    )
    val scaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    BottomSheetScaffold(
        sheetContent = {
            MainBottomSheet()
        },
        content = {
            ScrollableColumn {
                GoogleMaps(
                    currentLocation = currentLocation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    barberList = mockedMarkers(),

                )
            }
        },
        scaffoldState = scaffoldState
    )
}

fun mockedMarkers(): List<Barber> {
    return listOf(
        Barber(-22.91, -43.29, "Casa", "Sua casa"),
        Barber(-22.89, -43.29, "Bruna", "Casa da Bruna"),
    )
}

@Composable
fun MainBottomSheet() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        backgroundColor = Color.Gray
    ) {

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(HomeState(), LatLng(0.0, 0.0))
}

@Composable
fun ScrollableColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)) {
        content.invoke()
    }
}