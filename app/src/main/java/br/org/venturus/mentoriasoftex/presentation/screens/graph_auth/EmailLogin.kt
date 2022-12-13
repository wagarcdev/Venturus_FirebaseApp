package br.org.venturus.mentoriasoftex.presentation.screens.graph_auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.org.venturus.mentoriasoftex.MainViewModel
import br.org.venturus.mentoriasoftex.presentation.screens.components.MailPasswordFields
import br.org.venturus.mentoriasoftex.presentation.screens.components.MainMenuButton
import br.org.venturus.mentoriasoftex.presentation.ui.theme.venturusHorizontalBrush
import br.org.venturus.mentoriasoftex.presentation.ui.theme.venturusMidColor


@Composable
fun EmailLogin(
    navHostController: NavHostController,
) {

    val viewModel: MainViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        val validState = remember(
            email.value,
            password.value,
        ) {
            email.value.trim().isNotEmpty()
                    && password.value.trim().isNotEmpty()
        }
        val textFieldColors = Color.Blue


        val context = LocalContext.current

        Column(
            modifier = Modifier
                .imePadding()
                .navigationBarsPadding()
                .defaultMinSize(300.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom

        ) {

            LoginRow()

            MailPasswordFields(
                email,
                textFieldColors,
                password,
                navHostController
            )

            MainMenuButton(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                showIcon = false,
                enabled = validState,
                buttonVerticalPaddingDp = 8.dp,
                buttonMinHeight = 50.dp,
                iconDescription = "Log In Button",
                text = "Entrar",
                textFontSize = 20.sp,
                textAlign = TextAlign.Center,
                textFillMaxWidthFloat = 0.85f,
                disabledGradientBrush = Brush.horizontalGradient(
                    listOf(
                        Color.Gray,
                        Color.Gray
                    )
                ),
                enabledGradientBrush = venturusHorizontalBrush(),
                textColor = Color.White
            ) {
                viewModel.signInUser(
                    password,
                    email,
                    context,
                    navHostController
                )
            }



        }


    }

}


@Composable
fun LoginRow() {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Login",
            color = venturusMidColor,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


