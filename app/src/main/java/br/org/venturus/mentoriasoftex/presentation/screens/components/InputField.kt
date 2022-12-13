package br.org.venturus.mentoriasoftex.presentation.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.org.venturus.mentoriasoftex.presentation.ui.theme.venturusMidColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    isError: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "teste",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = {
        if (isError.value && valueState.value.isEmpty()) {
            Icon(Icons.Filled.Error, "error", tint = Color(0xFFFF0000))
        }
    },
) {

    val visualTransformation =
        if(!isPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = {
            Text(
                text = labelId,
                color = Color.Gray
            ) },
        shape = RoundedCornerShape(15.dp),
        leadingIcon = leadingIcon,
        isError = isError.value,
        colors = TextFieldDefaults.textFieldColors(
            textColor = venturusMidColor,
            cursorColor = venturusMidColor,
            focusedLabelColor = venturusMidColor,
            focusedIndicatorColor = venturusMidColor,
            unfocusedLabelColor = Color.Black,
            containerColor = Color(0x99FFFFFF)
        ),
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = Color.Black
        ),
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
    )
}
