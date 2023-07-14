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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.HorizontalSpacer
import com.designsystem.PrimaryMainButton
import com.entities.BarberShop

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state.value,
        onClickEvent = viewModel::onClickEvent
    )
}

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    onClickEvent: (HomeScreenEvent.ClickEvent, BarberShop) -> Unit = { _, _ -> }
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

        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(1f),
            content = {
                itemsIndexed(state.barberShops) { index, barberShop ->
                    BarberCard(barberShop = barberShop, onClickEvent = onClickEvent)
                }
            },
        )
    }
}

//Button(
//onClick = { /* Do something */ },
//// Assign reference "button" to the Button composable
//// and constrain it to the top of the ConstraintLayout
//modifier = Modifier.constrainAs(button) {
//    top.linkTo(parent.top, margin = 16.dp)
//}
//) {
//    Text("Button")
//}
//
//// Assign reference "text" to the Text composable
//// and constrain it to the bottom of the Button composable
//Text(
//"Text",
//Modifier.constrainAs(text) {
//    top.linkTo(button.bottom, margin = 16.dp)
//}
//)

@Composable
fun BarberCard(
    barberShop: BarberShop,
    onClickEvent: (HomeScreenEvent.ClickEvent, BarberShop) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp),
        backgroundColor = Color.White,
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (picture, title, snippet, distance) = createRefs()
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Red, CircleShape)
                    .constrainAs(picture) {
                        start.linkTo(parent.start, 12.dp)
                        top.linkTo(parent.top, 12.dp)
                        bottom.linkTo(parent.bottom, 12.dp)
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
                }
            )
            Text(
                text = "0.3km",
                style = TextStyle(fontSize = 12.sp),
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
    val state = HomeScreenState(
        isLoading = false,
        barberShops = getMockedBarberShops(),
        error = "",
        selectedBarberShop = null
    )
    HomeScreenContent(
        state = state,
        onClickEvent = { _, _ -> }
    )
}

fun getMockedBarberShops() = listOf(
    BarberShop(
        title = "Barbearia 1",
        address = "",
        snippet = "Pra quem sabe o que quer!",
        latitude = 0.0,
        longitude = 0.0,
        barbers = listOf(),
        services = listOf()
    ),
    BarberShop(
        title = "Barbearia 2",
        address = "",
        snippet = "A melhor barbearia do engenho!",
        latitude = 0.0,
        longitude = 0.0,
        barbers = listOf(),
        services = listOf()
    ),
    BarberShop(
        title = "Barbearia 3",
        address = "",
        snippet = "Especializados em cortes masculinos",
        latitude = 0.0,
        longitude = 0.0,
        barbers = listOf(),
        services = listOf()
    ),
)
