package com.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.designsystem.theme.PezinhoTheme

@Composable
fun SimpleHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.secondary
    )
}

@Composable
fun SimpleCaption(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onBackground
    )
}

@Preview
@Composable
fun TextsPreview() {
    PezinhoTheme {
        Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
            SimpleHeader("Esse é o cabeçalho simples")
            VerticalSpacer(dp = 24.dp)
            SimpleCaption(text = "Essa é a legenda simples")
        }
    }
}