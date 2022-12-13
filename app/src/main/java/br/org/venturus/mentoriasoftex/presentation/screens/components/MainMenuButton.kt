package br.org.venturus.mentoriasoftex.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MainMenuButton(
    modifier: Modifier = Modifier,
    buttonFillMaxWidthFloat: Float = 0.8f,
    buttonVerticalPaddingDp: Dp = 0.dp,
    buttonMinHeight: Dp = 40.dp,
    isLoading: MutableState<Boolean?> = mutableStateOf(false),
    showIcon: Boolean = true,
    iconID: Int = br.org.venturus.mentoriasoftex.R.drawable.venturus_logo,
    iconSize: Dp = 30.dp,
    iconDescription: String = "",
    iconWeight: Float = 0.1f,
    rowVerticalPadding: Dp = 2.dp,
    rowHorizontalPadding: Dp = 8.dp,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    textFontSize: TextUnit = 16.sp,
    textColor: Color = Color(0xFFFFFFFF),
    textFillMaxWidthFloat: Float = 0.90f,
    fontWeight: FontWeight = FontWeight.Bold,
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    cornerDp: Dp = 50.dp,
    enabledGradientBrush: Brush,
    disabledGradientBrush: Brush,
    enabled: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier then
                ( modifier
                    .padding(vertical = buttonVerticalPaddingDp)
                    .fillMaxWidth(buttonFillMaxWidthFloat)
                    .wrapContentHeight()
                    .clickable { onClick.invoke() }
                    .clip(RoundedCornerShape(cornerDp))
                    .background(
                        if (enabled) {
                            enabledGradientBrush
                        } else {
                            disabledGradientBrush
                        }
                    )
                        ),
        shape = RoundedCornerShape(cornerDp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x00000000),
            contentColor = Color(0x00000000),
            disabledContainerColor = Color(0x00000000),
            disabledContentColor = Color(0x00000000)
        )
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = buttonMinHeight)
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(
                    vertical = rowVerticalPadding,
                    horizontal = rowHorizontalPadding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(iconWeight)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showIcon) {
                    Image(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(iconSize),
                        painter = painterResource(
                            id = iconID
                        ),
                        contentDescription = iconDescription,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .fillMaxWidth(textFillMaxWidthFloat),
                    text = text,
                    fontSize = textFontSize,
                    fontWeight = fontWeight,
                    color = if (enabled) textColor else Color(0xFFD3D3D3),
                    maxLines = 1,
                    textAlign = textAlign
                )
            }

            val showLoadingIcon = remember { mutableStateOf(isLoading.value) }

            //DISPLAY ALIGNMENT ROW FOR CENTERING TEXT
            Column(
                modifier = Modifier
                    .weight(iconWeight)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showLoadingIcon.value == true) {
                    CircularProgressIndicator(
                        Modifier
                            .padding(end = 4.dp)
                            .size(iconSize)
                    )
                }
            }
        }
    }
}