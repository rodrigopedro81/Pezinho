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
        onTypeEvent = viewModel::onTypeEvent,
        onClickEvent = viewModel::onClickEvent,
        state = loginScreenState.value
    )
}

@Composable
fun LoginScreenContent(
    onTypeEvent: (LoginScreenEvent.TypeEvent, String) -> Unit,
    onClickEvent: (LoginScreenEvent.ClickEvent) -> Unit,
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
                onTypeEvent.invoke(LoginScreenEvent.TypeEvent.UPDATE_EMAIL, it)
            }
        )
        TextField(
            value = state.password,
            onValueChange = {
                onTypeEvent.invoke(LoginScreenEvent.TypeEvent.UPDATE_PASSWORD, it)
            }
        )
        Button(
            onClick = { onClickEvent.invoke(LoginScreenEvent.ClickEvent.CLICK_LOGIN) }
        ) {
            Text(text = "Logar")
        }
        Button(
            onClick = { onClickEvent.invoke(LoginScreenEvent.ClickEvent.CLICK_REGISTER) }
        ) {
            Text(text = "Registrar")
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        onTypeEvent = { _,_ -> },
        onClickEvent = { },
        state = LoginScreenState(),
    )
}
