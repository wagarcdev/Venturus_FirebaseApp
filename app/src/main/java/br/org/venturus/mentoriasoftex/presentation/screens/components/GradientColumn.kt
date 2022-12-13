package br.org.venturus.mentoriasoftex.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun GradientColumn(
    modifier: Modifier = Modifier,
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    columnVerticalArrangement: Arrangement.Vertical = Arrangement.Bottom,
    topSpacerTransition: Boolean = false,
    topSpacerTransitionHeight: Dp = 0.dp,
    bottomSpacerTransition: Boolean = false,
    bottomSpacerTransitionHeight: Dp = 0.dp,
    topTransitionVerticalGradient: Brush = Brush.verticalGradient(listOf(
        Color.Transparent,
        Color.Transparent
    )),
    columnVerticalGradient: Brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent)),
    bottomTransitionVerticalGradient: Brush = Brush.verticalGradient(listOf(
        Color.Transparent,
        Color.Transparent
    )),
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    contentVerticalArrangement: Arrangement.Vertical =  Arrangement.SpaceBetween,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = columnHorizontalAlignment,
        verticalArrangement = columnVerticalArrangement
    ) {


        if (topSpacerTransition) {
            GradientTransitionRows(
                transitionHeight = topSpacerTransitionHeight,
                gradient = topTransitionVerticalGradient
            )
        }



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(columnVerticalGradient),
            horizontalAlignment = contentHorizontalAlignment,
            verticalArrangement = contentVerticalArrangement
        ) {

            content.invoke()
        }

        if (bottomSpacerTransition) {
            GradientTransitionRows(
                transitionHeight = bottomSpacerTransitionHeight,
                gradient = bottomTransitionVerticalGradient
            )

        }


    }
}

@Composable
private fun GradientTransitionRows(
    transitionHeight: Dp,
    gradient: Brush
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(transitionHeight)
            .background(gradient)
    ) { }
}