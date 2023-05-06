package com.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreen(
    goToRegisterScreen: () -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val loginScreenState = viewModel.uiState.collectAsStateWithLifecycle()
    LoginScreenContent(
        onEvent = viewModel::onEvent,
        state = loginScreenState.value
    )
}

@Composable
fun LoginScreenContent(
    onEvent: (LoginScreenEvent, Any?) -> Unit,
    state: LoginScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TextField(
            value = state.email,
            onValueChange = {
                onEvent.invoke(LoginScreenEvent.UPDATE_EMAIL, it)
            }
        )
        TextField(
            value = state.password,
            onValueChange = {
                onEvent.invoke(LoginScreenEvent.UPDATE_PASSWORD, it)
            }
        )
        Button(
            onClick = { onEvent.invoke(LoginScreenEvent.CLICK_LOGIN, null) }
        ) {
            Text(text = "Logar")
        }
        Button(
            onClick = { onEvent.invoke(LoginScreenEvent.CLICK_REGISTER, null) }
        ) {
            Text(text = "Registrar")
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        onEvent = { _,_ -> },
        state = LoginScreenState(),
    )
}
