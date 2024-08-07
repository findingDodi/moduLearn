package de.hsd.modulearn.screens

import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.*

/**
 * Bildschirm zur Anzeige des Fortschritts und der erreichten Erfolge.
 *
 * Diese Funktion zeigt den Fortschritt des Benutzers anhand eines Kreises mit Prozentanzeige für
 * ein bestimmtes Modul, den aktuellen Streak und bis zu drei mögliche Erfolgsabzeichen.
 *
 * @param navController Der NavController für die Navigation innerhalb der Anwendung.
 * @param points Die Punktezahl des Benutzers.
 * @param showThirdBadge Gibt an, ob das dritte Abzeichen angezeigt werden soll.
 * @param moduleProgress Der Fortschritt des Benutzers im aktuellen Modul in Prozent.
 */
@Composable
fun ProgressScreen(navController: NavController, points: Int, showThirdBadge: Boolean, moduleProgress: Int) {
    val context = LocalContext.current

    // Fortschrittskreis und Prozentanzeige
    val progress = moduleProgress / 100f // assuming max points is 100

    val sharedPreferences = context.getSharedPreferences("de.hsd.modulearn.PREFERENCES", Context.MODE_PRIVATE)

    val streak = getStreak(sharedPreferences)

    val badgeImage1 = if (points >= 1000) R.drawable.points_badge_foreground else R.drawable.locked_badge_foreground
    val badgeImage2 = if (streak >= 7) R.drawable.streak_badge_foreground else R.drawable.locked_badge_foreground
    val badgeImage3 = if (showThirdBadge) R.drawable.module_finished_foreground else R.drawable.locked_badge_foreground


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
                    text = "Mein Fortschritt:",
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

                // Anzeige der Streak-Informationen
                Text(
                    text = if (streak == 1) {
                        "Du hast $streak Tag in Folge gelernt!"
                    } else {
                        "Du hast $streak Tage in Folge gelernt!"
                    },
                    style = Typography.headlineSmall,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )

                Text(
                    text = "Meine Badges:",
                    style = Typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)

                ) {
                    Image(
                        painter = painterResource(id = badgeImage1),
                        contentDescription = "Bild 1",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp) // optional: fügt etwas Abstand zwischen den Bildern hinzu
                            .aspectRatio(1f) // stellt sicher, dass das Bild quadratisch bleibt
                    )
                    Image(
                        painter = painterResource(id = badgeImage2),
                        contentDescription = "Bild 2",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp) // optional: fügt etwas Abstand zwischen den Bildern hinzu
                            .aspectRatio(1f) // stellt sicher, dass das Bild quadratisch bleibt
                    )
                    Image(
                        painter = painterResource(id = badgeImage3),
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

// Funktion zur Abfrage der Streak aus den SharedPreferences
fun getStreak(sharedPreferences: SharedPreferences): Int {
    return sharedPreferences.getInt("streak", 0)
}
