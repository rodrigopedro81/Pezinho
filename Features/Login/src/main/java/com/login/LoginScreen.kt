package com.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.MainEditText
import com.designsystem.PrimaryMainButton
import com.designsystem.SecondaryMainButton
import com.designsystem.SimpleHeader
import com.designsystem.VerticalSpacer
import com.maps.GoogleMaps

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
        SimpleHeader(text = "Login")
        VerticalSpacer(dp = 32.dp)
        MainEditText(
            isValid = true,
            label = "Email",
            hint = "Digite seu email",
            text = state.email,
            onTextChange = {
                onTypeEvent.invoke(LoginScreenEvent.TypeEvent.UPDATE_EMAIL, it)
            }
        )
        MainEditText(
            isValid = true,
            label = "Senha",
            hint = "Digite sua senha",
            text = state.password,
            onTextChange = {
                onTypeEvent.invoke(LoginScreenEvent.TypeEvent.UPDATE_PASSWORD, it)
            }
        )
        VerticalSpacer(dp = 20.dp)
        PrimaryMainButton(
            onClick = { onClickEvent.invoke(LoginScreenEvent.ClickEvent.CLICK_LOGIN) },
            isButtonEnabled = true,
            buttonText = "Logar"
        )
        VerticalSpacer(dp = 20.dp)
        SecondaryMainButton(
            onClick = { onClickEvent.invoke(LoginScreenEvent.ClickEvent.CLICK_REGISTER) },
            buttonText = "Registrar"
        )
        VerticalSpacer(dp = 20.dp)
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
