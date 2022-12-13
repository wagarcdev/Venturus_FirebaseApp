package br.org.venturus.mentoriasoftex.presentation.screens.components

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.org.venturus.mentoriasoftex.MainViewModel
import br.org.venturus.mentoriasoftex.R
import br.org.venturus.mentoriasoftex.google.GoogleApiContract
import br.org.venturus.mentoriasoftex.presentation.navigation.SoftexGraph
import com.google.android.gms.common.api.ApiException

@Composable
fun GoogleSignInButton(
    navHostController: NavHostController,
    buttonFillWidthFloat: Float = 0.8f,
) {
    val viewModel: MainViewModel = viewModel()

    val isLoadingState = viewModel.loadingState.observeAsState() as MutableState
    val isError = rememberSaveable { mutableStateOf(false) }

    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)

                if (gsa != null) {
                    viewModel.fetchSignInUser(gsa.email, gsa.displayName)
                    navHostController.navigate(SoftexGraph.APP)
                } else {
                    isError.value = true
                }
            } catch (e: ApiException) {
                Log.d("Error in AuthScreen%s", e.toString())
            }
        }

    MainMenuButton(
        enabled = true,
        buttonFillMaxWidthFloat = buttonFillWidthFloat,
        buttonVerticalPaddingDp = 8.dp,
        isLoading = isLoadingState,
        iconID = R.drawable.ic_google_logo,
        iconDescription = "Google Icon",
        buttonMinHeight = 50.dp,
        text = "Continuar com Google",
        textFontSize = 18.sp,
        textAlign = TextAlign.Center,
        textFillMaxWidthFloat = 0.85f,
        textColor = Color.DarkGray,
        fontWeight = FontWeight.Normal,
        borderStroke = BorderStroke(0.dp, Color.Transparent),
        enabledGradientBrush = Brush.horizontalGradient(
            listOf(
                Color(0xFFFFFFFF),
                Color(0xFFFFFFFF),
                Color(0xFFFFFFFF),
                Color(0xFFF0FFFF),
            )
        ),
        disabledGradientBrush = Brush.horizontalGradient(
            listOf(
                Color(0xFFFFFFFF),
                Color(0xFFFFFFFF),
            )
        ),
    ) { authResultLauncher.launch(signInRequestCode) }

    when {
        isError.value -> {
            isError.value.let {

                Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.auth_error_msg),
                    Toast.LENGTH_LONG
                ).show()

                viewModel.hideLoading()
            }
        }
    }
}