package com.home.screens.barberList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.entities.AvailableService
import com.entities.BarberShop
import com.navigation.Destinations
import com.navigation.navigateWithObject

@Composable
fun BarberListScreen(
    viewModel: BarberListScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    BarberListScreenContent(
        state = state.value,
        onClickEvent = { BarberShop ->
            navController.navigateWithObject(
                Destinations.Main.barberShopScreen.route,
                BarberShop
            )
        }
    )
}

@Composable
fun BarberListScreenContent(
    state: BarberListScreenState,
    onClickEvent: (BarberShop) -> Unit = { _ -> },
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .padding(16.dp)
                .background(Color.LightGray),
        ) {
            // TODO () -> O que entrará aqui?
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                itemsIndexed(state.barberShops) { index, barberShop ->
                    BarberCard(barberShop = barberShop, onClickEvent = onClickEvent)
                }
            },
        )
    }
}

@Composable
fun BarberCard(
    barberShop: BarberShop,
    onClickEvent: (BarberShop) -> Unit
) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(112.dp)
            .clickable { onClickEvent(barberShop) },
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (picture, title, snippet, distance) = createRefs()
            Box(
                modifier = Modifier
                    .background(Color.Red, CircleShape)
                    .constrainAs(picture) {
                        start.linkTo(parent.start, 12.dp)
                        top.linkTo(parent.top, 12.dp)
                        bottom.linkTo(parent.bottom, 12.dp)
                        width = Dimension.value(64.dp)
                        height = Dimension.value(64.dp)
                    },
            )
            Text(
                text = barberShop.title,
                style = TextStyle(fontSize = 24.sp),
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(picture.top)
                    start.linkTo(picture.end, margin = 16.dp)
                }
            )
            Text(
                text = barberShop.snippet,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.constrainAs(snippet) {
                    start.linkTo(title.start)
                    top.linkTo(title.bottom, margin = 8.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
            )
            Text(
                text = "0.3km",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.constrainAs(distance) {
                    end.linkTo(parent.end, margin = 24.dp)
                    top.linkTo(parent.top, margin = 24.dp)
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val state = BarberListScreenState(
        isLoading = false,
        barberShops = getMockedBarberShops(),
        error = "",
        selectedBarberShop = null
    )
    BarberListScreenContent(
        state = state,
        onClickEvent = { _ -> }
    )
}

fun getMockedBarberShops() = listOf(
    getMockedBarber(),
    getMockedBarber(),
    getMockedBarber(),
)

fun getMockedBarber() = BarberShop(
    wallpaper = "https://blog.consumer.com.br/wp-content/uploads/2020/11/culinária-regional-brasileira.jpg",
    title = "Barbearia do Zé",
    address = "Rua dos bobos, 0",
    services = listOf(
        AvailableService(
            id = 1,
            title = "Corte de cabelo",
            price = "R$ 30",
            description = "Corte de cabelo",
            duration = "45 minutos",
        ),
        AvailableService(
            id = 2,
            title = "Barba",
            price = "R$ 15",
            description = "Barba",
            duration = "10 minutos",
        ),
    ),
)
