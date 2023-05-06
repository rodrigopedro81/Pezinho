package com.pezinho

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(device = Devices.DESKTOP)
@Preview(device = Devices.NEXUS_5)
@Preview(device = Devices.PHONE)
@Preview(device = Devices.TABLET)
@Preview(device = Devices.PIXEL_2)
@Preview(device = Devices.WEAR_OS_LARGE_ROUND)
annotation class DevicePreview