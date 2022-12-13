package br.org.venturus.mentoriasoftex.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// My colors
val Amber200 = Color(0xFFffffe5)
val Amber500 = Color(0xFFffecb3)
val Turquoise200 = Color(0xFF88ffff)
val Turquoise500 = Color(0xFF4ce0d2)
val Turquoise700 = Color(0xFF00ada1)
val PineGreen200 = Color(0xFF4c9e90)
val PineGreen500 = Color(0xFF136f63)
val PineGreen700 = Color(0xFF004339)


//Venturus
val venturusMidColor = Color(0xFFC02F7B)

@Composable
fun venturusHorizontalBrush() = Brush.horizontalGradient(
    listOf(

        Color(0xFFE62369),
        Color(0xFFD4226D),
        Color(0xFFBA2573),
        Color(0xFF562E8B),
        Color(0xFF562E8B),

        )
)