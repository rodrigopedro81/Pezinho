package com.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.theme.PezinhoTheme

@Preview(device = Devices.DESKTOP)
@Preview(device = Devices.NEXUS_5)
@Preview(device = Devices.PHONE)
@Preview(device = Devices.TABLET)
@Preview(device = Devices.PIXEL_2)
@Preview(device = Devices.WEAR_OS_LARGE_ROUND)
annotation class DevicePreview

@Composable
fun WhiteBoard(
    content: @Composable () -> Unit
) {
    PezinhoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            content.invoke()
        }
    }
}