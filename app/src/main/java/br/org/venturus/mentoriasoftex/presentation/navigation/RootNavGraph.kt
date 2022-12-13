package br.org.venturus.mentoriasoftex.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.org.venturus.mentoriasoftex.presentation.navigation.nested.appNavGraph
import br.org.venturus.mentoriasoftex.presentation.navigation.nested.authNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavGraph() {

    val navHostController = rememberAnimatedNavController()

    AnimatedNavHost(
        modifier = Modifier
            .background(Color.Transparent),
        navController = navHostController,
        route = SoftexGraph.ROOT,
        startDestination = SoftexGraph.AUTH // TODO change back to = Graph.AUTH
    ) {
        authNavGraph(navHostController)
        appNavGraph(navHostController)
    }
}

object SoftexGraph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val APP = "app_graph"
}