package com.login.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.designsystem.theme.PezinhoTheme
import navigation.Routes

@Composable
fun LoginScreen(
    navigateTo: (String) -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val loginScreenState = viewModel.loginScreenState.collectAsStateWithLifecycle()
    LoginScreenContent(
        navigateTo = navigateTo,
        onTypeEvent = viewModel::onTypeEvent,
        onClickEvent = viewModel::onClickEvent,
        state = loginScreenState.value
    )
}

@Composable
fun LoginScreenContent(
    onTypeEvent: (LoginEvent.TypeEvent, String) -> Unit,
    onClickEvent: (LoginEvent.ClickEvent) -> Unit,
    state: LoginScreenState,
    navigateTo: (String) -> Unit
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
            onTextChange = { onTypeEvent.invoke(LoginEvent.TypeEvent.UPDATE_EMAIL, it) }
        )
        MainEditText(
            isValid = true,
            label = "Senha",
            hint = "Digite sua senha",
            text = state.password,
            onTextChange = {
                onTypeEvent.invoke(LoginEvent.TypeEvent.UPDATE_PASSWORD, it)
            }
        )
        VerticalSpacer(dp = 20.dp)
        PrimaryMainButton(
            onClick = {
                onClickEvent.invoke(LoginEvent.ClickEvent.CLICK_LOGIN)
                navigateTo.invoke(Routes.HomeContainer.destination)
            },
            isButtonEnabled = true,
            buttonText = "Logar"
        )
        VerticalSpacer(dp = 20.dp)
        SecondaryMainButton(
            onClick = {
                onClickEvent.invoke(LoginEvent.ClickEvent.CLICK_REGISTER)
                navigateTo.invoke(Routes.Register.destination)
            },
            buttonText = "Registrar"
        )
        VerticalSpacer(dp = 20.dp)
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    PezinhoTheme {
        LoginScreenContent(
            onTypeEvent = { _, _ -> },
            onClickEvent = { },
            state = LoginScreenState(),
            navigateTo = {}
        )
    }
}
