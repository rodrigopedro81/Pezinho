package com.home.screens.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.AutoCompleteTextField
import com.maps.GPSClient
import com.maps.ui.Maps

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state.value,
        onTypeEvent = viewModel::onTypeEvent
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    onTypeEvent: (HomeScreenEvent.TypeEvent, String) -> Unit = { _, _ -> }
) {
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Expanded,
        confirmStateChange = { false }
    )
    val scaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    BottomSheetScaffold(
        sheetContent = {
            MainBottomSheet(state, onTypeEvent)
        },
        content = {
            val barberCoordinates = state.barbers.map { Pair(it.latitude, it.longitude) }
            Maps(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                barbersCoordinates = barberCoordinates,
            )
        },
        scaffoldState = scaffoldState
    )
}

@Composable
fun MainBottomSheet(
    state: HomeScreenState,
    onTypeEvent: (HomeScreenEvent.TypeEvent, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        backgroundColor = Color.LightGray
    ) {
        AutoCompleteTextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = state.address,
            isTextValid = state.isAddressValid,
            autoCompletePredictions = state.autoCompletePredictions.map { it.formattedAddress },
            onSelect = { onTypeEvent(HomeScreenEvent.TypeEvent.SELECT_ADDRESS, it) },
            onSearch = { onTypeEvent(HomeScreenEvent.TypeEvent.NEW_SEARCH, it) }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    GPSClient.initialize(LocalContext.current)
    HomeScreenContent(
        state = HomeScreenState()
    )
}
