package com.home.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.AutoCompleteTextField
import com.designsystem.HorizontalSpacer
import com.designsystem.MainButtonPreview
import com.designsystem.PrimaryMainButton
import com.entities.AutoComplete
import com.entities.Barber
import com.entities.BarberShop
import com.maps.GPSClient

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state.value,
        onTypeEvent = viewModel::onTypeEvent
    )
}

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    onTypeEvent: (HomeScreenEvent.TypeEvent, String) -> Unit = { _, _ -> }
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .padding(16.dp)
                .background(Color.LightGray)
        ) {
            PrimaryMainButton(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                buttonText = "Autônomos",
                isButtonEnabled = true,
            ) {
            }
            HorizontalSpacer(dp = 16.dp)
            PrimaryMainButton(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                buttonText = "Barbearias",
                isButtonEnabled = true
            ) {
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            content = {
                itemsIndexed(state.barberShops) { index, barberShop ->
                    BarberCard(barberShop = barberShop, onTypeEvent = onTypeEvent)
                }
            },
        )
    }
}

@Composable
fun BarberCard(
    barberShop: BarberShop,
    onTypeEvent: (HomeScreenEvent.TypeEvent, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp),
        backgroundColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Red, CircleShape)
            ) {
                
            }
            HorizontalSpacer(dp = 24.dp)

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = barberShop.title, style = TextStyle(fontSize = 24.sp))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    val state = HomeScreenState(
        isLoading = false,
        barberShops = getMockedBarberShops(),
        error = "",
        autoCompletePredictions = listOf(),
        address = "",
        isAddressValid = true
    )
    HomeScreenContent(
        state = state
    )
}

private fun getMockedBarberShops() = listOf(
    BarberShop(
        title = "Barbearia 1",
    ),
    BarberShop(
        title = "Barbearia 2",
    ),
    BarberShop(
        title = "Barbearia 3",
    ),
)
