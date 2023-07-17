package com.login.screens.login

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.designsystem.MainEditText
import com.designsystem.PrimaryMainButton
import com.designsystem.SecondaryMainButton
import com.designsystem.SimpleHeader
import com.designsystem.VerticalSpacer
import com.designsystem.theme.PezinhoTheme
import com.entities.AuthResult
import com.navigation.Destinations
import com.navigation.navigateToOtherContainer

@Composable
fun LoginScreen(
    mainNavController: NavHostController,
    loginContainerNavController: NavHostController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val loginScreenState = viewModel.state.collectAsStateWithLifecycle()
    LoginScreenContent(
        mainNavController = mainNavController,
        loginContainerNavController = loginContainerNavController,
        onTypeEvent = viewModel::onTypeEvent,
        onAuthEvent = viewModel::onAuthEvent,
        state = loginScreenState.value,
    )
}

@Composable
fun LoginScreenContent(
    mainNavController: NavHostController,
    loginContainerNavController: NavHostController,
    onTypeEvent: (LoginEvent.TypeEvent, String) -> Unit,
    onAuthEvent: (LoginEvent.AuthEvent, (AuthResult) -> Unit) -> Unit,
    state: LoginScreenState,
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
                        mainNavController.navigateToOtherContainer(Destinations.Main.barberListScreen)
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
            onClick = { loginContainerNavController.navigateToOtherContainer(Destinations.Login.loginScreen) },
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
            mainNavController = rememberNavController(),
            loginContainerNavController = rememberNavController()
        )
    }
}
