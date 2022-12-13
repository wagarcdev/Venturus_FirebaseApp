package br.org.venturus.mentoriasoftex.presentation.navigation.nested

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import br.org.venturus.mentoriasoftex.presentation.navigation.SoftexGraph
import br.org.venturus.mentoriasoftex.presentation.screens.ScreenView
import br.org.venturus.mentoriasoftex.presentation.screens.graph_auth.AuthStart
import br.org.venturus.mentoriasoftex.presentation.screens.graph_auth.EmailLogin
import br.org.venturus.mentoriasoftex.presentation.screens.graph_auth.RegisterScreen
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {

    navigation(
        route = SoftexGraph.AUTH,
        startDestination = AuthScreens.AuthStart.route

    ) {

        composable(AuthScreens.AuthStart.route) {

           ScreenView {
               AuthStart(navHostController)
           }

        }

        composable(AuthScreens.Register.route) {

            ScreenView() {
                RegisterScreen(navHostController)
            }
        }

        composable(AuthScreens.EmailLogin.route) {

            ScreenView {
                EmailLogin(navHostController)
            }
        }



    }
}

sealed class AuthScreens(val route: String) {
    object AuthStart : AuthScreens(route = "AUTH_START")
    object Login : AuthScreens(route = "LOGIN")
    object EmailLogin : AuthScreens(route = "EMAIL_LOGIN")
    object Register : AuthScreens(route = "REGISTER")

}