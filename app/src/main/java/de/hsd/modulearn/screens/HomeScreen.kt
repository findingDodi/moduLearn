package de.hsd.modulearn.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { Header(title = "moduLearn") },
        bottomBar = { Footer(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp), // Festen Abstand zwischen den Elementen
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                    onClick = {
                        navController.navigate("OOP1LectureScreen")
                              },
                    colors = CardDefaults.cardColors(
                    containerColor = PrimaryDarkBlue,
                    contentColor = White
                ), modifier = Modifier
                    .width(250.dp)
                    .height(100.dp),
            ) {
                Text(
                    text = "OOP1",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Right,
                    style = Typography.headlineSmall
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryDarkBlue,
                    contentColor = White
                ), modifier = Modifier
                    .width(250.dp)
                    .height(100.dp),
            ) {
                Text(
                    text = "MCI",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Right,
                    style = Typography.headlineSmall
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = LightGrey,
                    contentColor = PrimaryDarkBlue
                ), modifier = Modifier
                    .width(250.dp)
                    .height(100.dp),
            ) {
                Text(
                    text = "+",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    style = Typography.titleLarge
                )
            }
        }
    }
}