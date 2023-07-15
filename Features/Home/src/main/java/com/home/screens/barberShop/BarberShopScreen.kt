package com.home.screens.barberShop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.entities.AvailableService
import com.entities.BarberShop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@Composable
fun BarberShopScreen(
    navController: NavController,
    selectedBarberShop: BarberShop?,
    viewModel: BarberShopScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    BarberShopScreenContent(
        selectedBarberShop = selectedBarberShop,
    )
}

@Composable
fun BarberShopScreenContent(
    selectedBarberShop: BarberShop?
) {
    if (selectedBarberShop == null) {
        // TODO () -> Mostrar que algo deu errado
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = selectedBarberShop.title,
                style = TextStyle(
                    fontSize = 24.sp,
                ),
                modifier = Modifier.padding(16.dp),
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                itemsIndexed(
                    items = selectedBarberShop.services,
                    key = { _, item ->
                        item.id
                    },
                ) { _, item ->
                    AvailableServiceItem(
                        availableService = item,
                    )
                }
            }
        }
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
    val mockedBarberShop = BarberShop(
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
            AvailableService(
                id = 3,
                title = "Corte de cabelo + Barba",
                description = "Corte de cabelo + Barba",
                price = "R$ 40",
                duration = "1 hora",
            ),
        ),
    )
    BarberShopScreenContent(selectedBarberShop = mockedBarberShop)
}

data class BarberShopScreenState(
    val selectedServices: List<AvailableService> = listOf()
)

@HiltViewModel
class BarberShopScreenViewModel @Inject constructor(): ViewModel() {

    private val _state : MutableStateFlow<BarberShopScreenState> =
        MutableStateFlow(BarberShopScreenState())
    val state = _state.asStateFlow()
}