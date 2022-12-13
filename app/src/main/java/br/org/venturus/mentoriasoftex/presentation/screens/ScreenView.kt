package br.org.venturus.mentoriasoftex.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.org.venturus.mentoriasoftex.R
import br.org.venturus.mentoriasoftex.presentation.screens.components.GradientColumn
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenView(
    content: @Composable () -> Unit
) {
    rememberSystemUiController().setNavigationBarColor(Color(0xFFFFFFFF))

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0x00FFFFFF)
    ) {
        
        BackgroundImageRow(R.drawable.venturus_background)

        LogoSpace(R.drawable.venturus_logo)

        AuthScreenGradientColumn(content)
    }
}


@Composable
private fun BackgroundImageRow(
    imageId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(

            modifier = Modifier
                .fillMaxHeight(),
            painter = painterResource(id = imageId),
            contentDescription = "background image",
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun LogoSpace(
    imageId: Int,
    paddingTop: Dp = 64.dp
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(paddingTop))

        Image(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
                .scale(0.9f),
            painter = painterResource(id = imageId),
            contentDescription = "Login"
        )
    }
}


@Composable
fun AuthScreenGradientColumn(content: @Composable () -> Unit) {

    val transitionSmootherValue = 32.dp

    GradientColumn(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        columnHorizontalAlignment = Alignment.CenterHorizontally,
        columnVerticalArrangement = Arrangement.Bottom,
        topSpacerTransition = true,
        topSpacerTransitionHeight = transitionSmootherValue,
        topTransitionVerticalGradient = Brush.verticalGradient(
            listOf(
                Color.Transparent,
                Color(0x40FFFFFF)
            )
        ),
        columnVerticalGradient = Brush.verticalGradient(
            listOf(
                Color(0x40FFFFFF),
                Color(0x80FFFFFF),
                Color(0xBFFFFFFF),
                Color(0xFFFFFFFF),

            )
        ),
        bottomSpacerTransition = false,
        contentHorizontalAlignment = Alignment.CenterHorizontally,
        contentVerticalArrangement = Arrangement.SpaceBetween,
    ) {

        Spacer(modifier = Modifier.height(transitionSmootherValue))

        content()
    }
}

