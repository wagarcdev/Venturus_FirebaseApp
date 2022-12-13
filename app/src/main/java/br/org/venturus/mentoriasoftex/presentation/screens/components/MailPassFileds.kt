package br.org.venturus.mentoriasoftex.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun MailPasswordFields(
    email: MutableState<String>,
    textFieldColors: Color,
    password: MutableState<String>,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(1.0f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val isEmailError = remember { mutableStateOf(false) }
        val isPasswordError = remember { mutableStateOf(false) }


        val localFocusManager = LocalFocusManager.current
        val focusRequester = FocusRequester()


        InputField(
            valueState = email,
            labelId = "E-mail",
            enabled = true,
            isSingleLine = true,
            isError = isEmailError,
            errorMessage = "Preencha o email",
            focusRequester = focusRequester
        )



        InputField(
            valueState = password,
            labelId = "Senha",
            enabled = true,
            isSingleLine = true,
            isPassword = true,
            isError = isPasswordError,
            errorMessage = "Preencha a senha",
            focusRequester = focusRequester
        )

    }




}