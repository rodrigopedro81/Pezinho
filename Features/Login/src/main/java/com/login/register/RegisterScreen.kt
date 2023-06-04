package com.login.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.MainEditText
import com.designsystem.PrimaryMainButton
import com.designsystem.SecondaryMainButton
import com.designsystem.SimpleHeader
import com.designsystem.VerticalSpacer
import com.entities.AuthResult
import navigation.Routes

@Composable
fun RegisterScreen(
    navigateTo: (String) -> Unit,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {
    val registerScreenState = viewModel.state.collectAsStateWithLifecycle()
    RegisterScreenContent(
        navigateTo = navigateTo,
        onRegister = viewModel::onRegisterEvent,
        onClickEvent = viewModel::onClickEvent,
        onTypeEvent = viewModel::onTypeEvent,
        state = registerScreenState.value,
    )
}

@Composable
fun RegisterScreenContent(
    navigateTo: (String) -> Unit,
    onRegister: ((AuthResult) -> Unit) -> Unit,
    onTypeEvent: (RegisterEvent.TypeEvent, String) -> Unit,
    onClickEvent: (RegisterEvent.ClickEvent) -> Unit,
    state: RegisterScreenState
) {
    AnimatedVisibility(visible = state.isClient == null) {
        Column(modifier = Modifier.fillMaxSize()) {
            SimpleHeader(text = "Registrar")
            VerticalSpacer(dp = 50.dp)
            PrimaryMainButton(
                buttonText = "Sou Barbeiro",
                isButtonEnabled = true,
                onClick = { onClickEvent.invoke(RegisterEvent.ClickEvent.CLICK_BARBER) }
            )
            VerticalSpacer(dp = 16.dp)
            SecondaryMainButton(
                buttonText = "Sou Cliente",
                onClick = { onClickEvent.invoke(RegisterEvent.ClickEvent.CLICK_CLIENT) }
            )
        }
    }
    AnimatedVisibility(visible = state.isClient != null) {
        val scrollState: ScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            VerticalSpacer(dp = 16.dp)
            if (state.isClient == true) {
                SimpleHeader(text = "Cliente")
                BasicForm(state, onTypeEvent)
            } else {
                SimpleHeader(text = "Barbeiro")
                BarberForm(state, onTypeEvent)
            }
            PrimaryMainButton(
                buttonText = "Registrar",
                isButtonEnabled = state.isFormValid,
                onClick = {
                    onRegister.invoke() {
                        if (it.success) {
                            navigateTo.invoke(Routes.HomeContainer.destination)
                        } else {
                            // TODO () -> O que fazer se registrar falhar?
                        }
                    }
                }
            )
            VerticalSpacer(dp = 16.dp)
        }
    }
}

@Composable
fun BarberForm(
    state: RegisterScreenState,
    onTypeEvent: (RegisterEvent.TypeEvent, String) -> Unit,
) {
    BasicForm(state = state, onTypeEvent = onTypeEvent)
    MainEditText(
        onTextChange = { onTypeEvent.invoke(RegisterEvent.TypeEvent.UPDATE_ADDRESS, it) },
        text = state.address,
        hint = "Qual seu endereço?",
        isValid = state.isAddressValid,
        label = "Endereço",
    )
    VerticalSpacer(dp = 16.dp)
}

@Composable
fun BasicForm(
    state: RegisterScreenState,
    onTypeEvent: (RegisterEvent.TypeEvent, String) -> Unit
) {
    MainEditText(
        onTextChange = { onTypeEvent.invoke(RegisterEvent.TypeEvent.UPDATE_NAME, it) },
        text = state.name,
        hint = "Qual seu nome e sobrenome?",
        isValid = state.isNameValid,
        label = "Nome e sobrenome",
    )
    VerticalSpacer(dp = 16.dp)
    MainEditText(
        onTextChange = { onTypeEvent.invoke(RegisterEvent.TypeEvent.UPDATE_EMAIL, it) },
        text = state.email,
        hint = "Qual seu email?",
        isValid = state.isEmailValid,
        label = "Email",
    )
    VerticalSpacer(dp = 16.dp)
    MainEditText(
        onTextChange = { onTypeEvent.invoke(RegisterEvent.TypeEvent.UPDATE_PASSWORD, it) },
        text = state.password,
        hint = "Qual sua senha?",
        isValid = state.isPasswordValid,
        label = "Senha",
    )
    VerticalSpacer(dp = 16.dp)
    MainEditText(
        onTextChange = { onTypeEvent.invoke(RegisterEvent.TypeEvent.UPDATE_PHONE, it) },
        text = state.phone,
        hint = "Qual seu telefone?",
        isValid = state.isPhoneValid,
        label = "Telefone",
    )
    VerticalSpacer(dp = 16.dp)
}
