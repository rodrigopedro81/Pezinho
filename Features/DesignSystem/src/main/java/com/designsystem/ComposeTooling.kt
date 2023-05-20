package com.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.theme.PezinhoTheme

/**
 * This annotation is used to preview the component in all devices.
 */
@Preview(device = Devices.DESKTOP)
@Preview(device = Devices.NEXUS_5)
@Preview(device = Devices.PHONE)
@Preview(device = Devices.TABLET)
@Preview(device = Devices.PIXEL_2)
@Preview(device = Devices.WEAR_OS_LARGE_ROUND)
annotation class DevicePreview

/**
 * This composable is used to preview the component in a white background.
 */
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

/**
 * This component is used to avoid preview errors
 * when using complex components like maps and camera.
 */
@Composable
fun NoPreviewComponent(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    component: @Composable (Modifier) -> Unit
) {
    if (LocalInspectionMode.current) {
        Card(modifier = modifier, backgroundColor = color) {
            Text(text = "No preview available, method NoPreviewComponent")
        }
    } else {
        component(modifier)
    }
}
