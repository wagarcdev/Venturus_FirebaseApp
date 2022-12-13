package br.org.venturus.mentoriasoftex.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.org.venturus.mentoriasoftex.MainViewModel

@Composable
fun SendScreenClicks(navController: NavHostController) {

    val viewModel: MainViewModel = viewModel()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        viewModel.getTotalClicks()
        Text(text = "<Cliques Totais>")
        Button(
            onClick = { viewModel.sendClicksToFirebase() },
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = "Enviar cliques para o Firebase")
        }

        OutlinedButton(
            onClick = {  },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "TOTAL CLICKS = ${viewModel.totalClicks}")
        }
    }
}