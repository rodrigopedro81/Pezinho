package com.home.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.maps.GoogleMaps

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMaps()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenContent()
}