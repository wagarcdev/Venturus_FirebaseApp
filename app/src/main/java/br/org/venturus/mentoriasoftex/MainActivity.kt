package br.org.venturus.mentoriasoftex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import br.org.venturus.mentoriasoftex.presentation.navigation.RootNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Transparency for StatusBar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            RootNavGraph()
        }
    }
}
