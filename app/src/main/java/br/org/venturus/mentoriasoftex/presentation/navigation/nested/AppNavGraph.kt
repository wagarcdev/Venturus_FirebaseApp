package br.org.venturus.mentoriasoftex.presentation.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.org.venturus.mentoriasoftex.presentation.navigation.SoftexGraph
import br.org.venturus.mentoriasoftex.presentation.screens.graph_app.HomeScreen


fun NavGraphBuilder.appNavGraph(navHostController: NavHostController) {

    navigation(
        route = SoftexGraph.APP,
        startDestination = AppScreens.Home.route
    ) {

        composable(AppScreens.Home.route) {

            HomeScreen(navHostController)

        }



    }

}

sealed class AppScreens(val route: String) {
    object Home : AppScreens(route = "HOME")
    object Profile : AppScreens(route = "PROFILE")
    object ProfileSelect : AppScreens(route = "PROFILE_SELECT")
}