package com.home.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maps.GPSClient
import com.maps.OpenSourceMaps

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state.value,
        onTypeEvent = viewModel::onTypeEvent
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    state: HomeState,
    onTypeEvent: (HomeEvent.TypeEvent, String) -> Unit = { _, _ -> }
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
            OpenSourceMaps(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )
        },
        scaffoldState = scaffoldState
    )
}

@Composable
fun MainBottomSheet(
    state: HomeState,
    onTypeEvent: (HomeEvent.TypeEvent, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        backgroundColor = Color.LightGray
    ) {
        Column {
            AnimatedVisibility(
                state.selectedAddress.not(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                LazyColumn {
                    items(
                        state.autoCompletePredictions,
                        itemContent = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable {
                                        onTypeEvent.invoke(
                                            HomeEvent.TypeEvent.SELECT_ADDRESS,
                                            it.address
                                        )
                                    }
                            ) {
                                Text(it.address)
                            }
                        }
                    )
                }
                Spacer(Modifier.height(16.dp))
            }
            TextField(
                value = state.address,
                onValueChange = {
                    onTypeEvent.invoke(HomeEvent.TypeEvent.NEW_SEARCH, it)
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    GPSClient.initialize(LocalContext.current)
    HomeScreenContent(
        state = HomeState()
    )
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