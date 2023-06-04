package com.login.login

import android.util.Log
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
import com.entities.AuthResult
import navigation.Routes

@Composable
fun LoginScreen(
    navigateTo: (String) -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val loginScreenState = viewModel.state.collectAsStateWithLifecycle()
    LoginScreenContent(
        navigateTo = navigateTo,
        onTypeEvent = viewModel::onTypeEvent,
        onAuthEvent = viewModel::onAuthEvent,
        state = loginScreenState.value,
    )
}

@Composable
fun LoginScreenContent(
    onTypeEvent: (LoginEvent.TypeEvent, String) -> Unit,
    onAuthEvent: (LoginEvent.AuthEvent, (AuthResult) -> Unit) -> Unit,
    state: LoginScreenState,
    navigateTo: (String) -> Unit,
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
                onAuthEvent.invoke(LoginEvent.AuthEvent.CLICK_LOGIN) { result ->
                    if (result.success) {
                        navigateTo.invoke(Routes.HomeContainer.destination)
                    } else {
                        Log.d("Teste", "deu ruim por causa disso -> ${result.error}")
                        // TODO () -> O que fazer caso não logue?
                    }
                }
            },
            isButtonEnabled = true,
            buttonText = "Logar"
        )
        VerticalSpacer(dp = 20.dp)
        SecondaryMainButton(
            onClick = { navigateTo.invoke(Routes.Register.destination) },
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
            onAuthEvent = { _, _ -> },
            state = LoginScreenState(),
            navigateTo = {},
        )
    }
}
