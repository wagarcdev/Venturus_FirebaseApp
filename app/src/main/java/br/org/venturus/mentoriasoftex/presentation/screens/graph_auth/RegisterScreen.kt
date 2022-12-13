package br.org.venturus.mentoriasoftex.presentation.screens.graph_auth

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.org.venturus.mentoriasoftex.domain.model.UserProfile
import br.org.venturus.mentoriasoftex.presentation.navigation.nested.AuthScreens
import br.org.venturus.mentoriasoftex.presentation.screens.components.InputField
import br.org.venturus.mentoriasoftex.presentation.screens.components.MainMenuButton
import br.org.venturus.mentoriasoftex.presentation.ui.theme.venturusHorizontalBrush
import br.org.venturus.mentoriasoftex.presentation.ui.theme.venturusMidColor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
) {


    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirm = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .imePadding()
            .navigationBarsPadding()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            text = "Cadastre-se",
            color = venturusMidColor,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(1.0f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val isEmailError = remember {
                mutableStateOf(false)
            }

            val isPasswordError = remember {
                mutableStateOf(false)
            }

            val isPasswordConfirmError = remember {
                mutableStateOf(false)
            }


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

            InputField(
                valueState = passwordConfirm,
                labelId = "Repita a senha",
                enabled = true,
                isSingleLine = true,
                isPassword = true,
                isError = isPasswordConfirmError,
                errorMessage = "Preencha a confirmação da senha",
                focusRequester = focusRequester
            )

        }

        val validState = remember(
            email.value,
            password.value,
            passwordConfirm.value
        ) {
            email.value.trim().isNotEmpty()
                    && password.value.trim().isNotEmpty()
                    && passwordConfirm.value.trim().isNotEmpty()
                    && password.value == passwordConfirm.value
        }



        EmailSignUpButton(
            validState,
            email,
            password,
            navHostController
        )



    }
}

@Composable
private fun EmailSignUpButton(
    validState: Boolean,
    email: MutableState<String>,
    password: MutableState<String>,
    navHostController: NavHostController
) {

    val db = Firebase.firestore
    val coroutineScope = rememberCoroutineScope()
    var user: UserProfile

    MainMenuButton(
        modifier = Modifier
            .padding(vertical = 8.dp),
        showIcon = false,
        buttonMinHeight = 50.dp,
        text = "Cadastrar",
        textFontSize = 20.sp,
        textColor = Color.White,
        borderStroke = BorderStroke(0.dp, Color.Transparent),
        enabledGradientBrush = venturusHorizontalBrush(),
        disabledGradientBrush = Brush.verticalGradient(
            listOf(
                Color.Transparent,
                Color.Transparent
            )
        ),
        enabled = validState,
        onClick = {
            if (validState) {
                user = UserProfile(
                    uid = "",
                    name = "",
                    email = email.value,
                    password = password.value
                )
                coroutineScope.launch {
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "ADD_USER",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w("ADD_USER", "Error adding document", e)
                        }
                }

                navHostController.navigate(AuthScreens.AuthStart.route)
            }

        },
    )
}

