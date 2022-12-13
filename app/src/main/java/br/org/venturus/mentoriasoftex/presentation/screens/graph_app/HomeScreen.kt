package br.org.venturus.mentoriasoftex.presentation.screens.graph_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.org.venturus.mentoriasoftex.R

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.firebase2),
            contentDescription = "Firebase image",
            modifier = Modifier.clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                      //TODO onClick
//                navController.navigate(Routes.SendClicks.route)
                      },
        ) {
            Text(text = "INICIAR")
        }
    }
}