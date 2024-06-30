package de.hsd.modulearn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.DarkMidGrey
import de.hsd.modulearn.theme.PrimaryDarkBlue
import de.hsd.modulearn.theme.PrimaryMidBlue
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White

@Composable
fun ProgressScreen(navController: NavController, points: Int) {
    Scaffold(
        topBar = { Header("Erfolge", false, navController) },
        bottomBar = { Footer(navController, 3) },
        floatingActionButton = { ButtonChatBot(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Mein Fortschritt",
                    style = Typography.headlineMedium,
                )

                // Box im selben Stil wie Module in der HomeScreen
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(7.5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(PrimaryMidBlue) // Verwenden der gleichen Farbe wie im OOP1 Modul
                        .padding(15.dp)
                ) {
                    // Fortschrittskreis und Prozentanzeige
                    val progress = points / 1000f // assuming max points is 1000
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp) // Größe des Fortschrittskreises anpassen
                            .clip(RoundedCornerShape(5.dp))
                            .padding(8.dp)
                    ) {
                        CircularProgressIndicator(
                            progress = progress,
                            color = PrimaryDarkBlue,
                            strokeWidth = 8.dp,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                        Text(
                            text = "${(progress * 100).toInt()}%",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // Text rechts neben dem Fortschrittskreis
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Du hast dieses Modul zu ${(progress * 100).toInt()}% abgeschlossen.",
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    val badge1 = if (points >= 250) R.drawable.modul_finished_badge_foreground else R.drawable.locked_badge_foreground
                    Image(
                        painter = painterResource(id = badge1),
                        contentDescription = "Bild 1",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp) // optional: fügt etwas Abstand zwischen den Bildern hinzu
                            .aspectRatio(1f) // stellt sicher, dass das Bild quadratisch bleibt
                    )
                    Image(
                        painter = painterResource(id = R.drawable.locked_badge_foreground), // Ersetze image2 mit deiner Drawable-Ressource
                        contentDescription = "Bild 2",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp) // optional: fügt etwas Abstand zwischen den Bildern hinzu
                            .aspectRatio(1f) // stellt sicher, dass das Bild quadratisch bleibt
                    )
                    Image(
                        painter = painterResource(id = R.drawable.locked_badge_foreground), // Ersetze image3 mit deiner Drawable-Ressource
                        contentDescription = "Bild 3",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp) // optional: fügt etwas Abstand zwischen den Bildern hinzu
                            .aspectRatio(1f) // stellt sicher, dass das Bild quadratisch bleibt
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(navController = NavController(LocalContext.current), points = 700)
}
