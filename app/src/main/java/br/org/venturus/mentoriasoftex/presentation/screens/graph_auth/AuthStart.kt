package br.org.venturus.mentoriasoftex.presentation.screens.graph_auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.org.venturus.mentoriasoftex.presentation.navigation.nested.AuthScreens
import br.org.venturus.mentoriasoftex.presentation.screens.components.GoogleSignInButton
import br.org.venturus.mentoriasoftex.presentation.screens.components.MainMenuButton
import br.org.venturus.mentoriasoftex.presentation.ui.theme.venturusHorizontalBrush
import br.org.venturus.mentoriasoftex.presentation.ui.theme.venturusMidColor


@Composable
fun AuthStart(
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val spacerHeight = 12.dp



        Spacer(modifier = Modifier.height(spacerHeight))

        StartButton(navHostController)

        Spacer(modifier = Modifier.height(spacerHeight))

        EmailLoginStartButton(navHostController)

        Spacer(modifier = Modifier.height(spacerHeight))

        GoogleSignInButton(navHostController)

        Spacer(modifier = Modifier.height(spacerHeight))

    }
}


@Composable
private fun EmailLoginStartButton(navHostController: NavHostController) {

    MainMenuButton(
        enabled = true,
        showIcon = false,
        text = "Entrar",
        textColor = venturusMidColor,
        textFontSize = 22.sp,
        buttonMinHeight = 75.dp,
        borderStroke = BorderStroke(0.dp, Color.Transparent),
        disabledGradientBrush = Brush.verticalGradient(
            listOf(
                Color.Transparent,
                Color.Transparent
            )
        ),
        enabledGradientBrush = Brush.verticalGradient(
            listOf(
                Color.Transparent,
                Color.Transparent
            )
        ),
        onClick = {
            navHostController
                .navigate(AuthScreens.EmailLogin.route)
        }
    )
}


@Composable
private fun StartButton(navHostController: NavHostController) {
    MainMenuButton(
        enabled = true,
        showIcon = false,
        text = "Cadastrar",
        textColor = Color(0xFFFFFFFF),
        textFontSize = 20.sp,
        buttonMinHeight = 50.dp,
        borderStroke = BorderStroke(0.dp, Color(0xFFFFFFFF)),
        disabledGradientBrush = Brush.horizontalGradient(
            listOf(
                Color(0xFFFFFFFF),
                Color(0xFFFFFFFF)
            )
        ),
        enabledGradientBrush = venturusHorizontalBrush(),
        onClick = {
            navHostController
                .navigate(AuthScreens.Register.route)
        }
    )
}

