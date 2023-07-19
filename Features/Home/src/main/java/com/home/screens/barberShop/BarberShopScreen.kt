package com.home.screens.barberShop

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.entities.AvailableService
import com.entities.BarberShop
import com.home.R
import com.home.screens.barberList.getMockedBarber
import com.navigation.Destinations
import kotlinx.coroutines.launch

@Composable
fun BarberShopScreen(
    navController: NavController,
    selectedBarberShop: BarberShop?,
    viewModel: BarberShopScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        if (selectedBarberShop == null) {
            navController.navigate(Destinations.Main.barberListScreen.route)
        }
    }
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
    selectedBarberShop: BarberShop,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val (image, bottomSheet) = createRefs()
        Wallpaper(
            url = selectedBarberShop.wallpaper,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
                height = Dimension.value(200.dp)
                centerHorizontallyTo(parent)
            },
        )
        BottomSheetScaffold(
            backgroundColor = Color.Transparent,
            sheetBackgroundColor = Color.Transparent,
            scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberBottomSheetState(
                    initialValue = BottomSheetValue.Expanded,
                    confirmStateChange = { false }
                )
            ),
            sheetGesturesEnabled = false,
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                )
            },
            sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
            content = {},
            modifier = Modifier.constrainAs(bottomSheet) {
                top.linkTo(image.bottom, (-24).dp)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
        )
    }
}

@Composable
fun Wallpaper(
    modifier: Modifier = Modifier,
    url: String
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        onError = {
            Log.e("coil", "${it.result.throwable}")
        },
        modifier = modifier
    )
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
        selectedBarberShop = getMockedBarber(),
    )
}
