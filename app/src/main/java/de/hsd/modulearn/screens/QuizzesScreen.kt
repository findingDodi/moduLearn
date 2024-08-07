package de.hsd.modulearn.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.MainActivity
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes.quizview
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.theme.LightMidGrey
import de.hsd.modulearn.theme.PrimaryDarkLilac
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White
import de.hsd.modulearn.utils.AssetLoader

/**
 * Bildschirm zur Anzeige einer Übersicht der Quizze.
 *
 * Diese Funktion zeigt eine Liste von Quizzen an, die der Benutzer auswählen kann.
 *
 * @param navController Der NavController für die Navigation innerhalb der Anwendung.
 */
@Composable
fun QuizzesScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("Quizzes", false, navController) },
        bottomBar = { Footer(navController, 2) },
        floatingActionButton = { ButtonChatBot(navController = navController) }

    ) {innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val lectureList = AssetLoader().fullLectureList

                Text(
                    text = "Quizübersicht",
                    style = Typography.headlineMedium,
                )

                lectureList.forEach { lecture ->
                    val context = LocalContext.current
                    val mainActivity = context as MainActivity
                    val unlockedModules = mainActivity.getUnlockedModules()

                    if (lecture.id <= unlockedModules) {
                        QuizzesItem(lecture, navController)
                    } else {
                        LockedQuizzesItem()
                    }
                }
            }
        }
    }
}

/**
 * Komponente zur Anzeige eines einzelnen Quiz-Eintrags.
 *
 * Diese Funktion zeigt ein einzelnes Quiz mit seinem Titel und einer Schaltfläche zur Navigation
 * zu den Details des Quiz an.
 *
 * @param lecture Das Quiz-Objekt, das angezeigt werden soll.
 * @param navController Der NavController für die Navigation innerhalb der Anwendung.
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun QuizzesItem (
    lecture: Lecture,
    navController:NavController
) {
    //Für die Punkte: Kontext und MainActivity-Instanz abrufen
    val context = LocalContext.current
    val mainActivity = context as MainActivity

    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(3.25f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryDarkLilac)
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    //Für die Punkte: Punkte basierend auf dem Modultitel erhöhen
                    when (lecture.title) {
                        "OOP1" -> mainActivity.setPoints(mainActivity.getPoints() + 20)
                        "MCI" -> mainActivity.setPoints(mainActivity.getPoints() + 55)
                        // Fügen Sie hier weitere Module und entsprechende Punkte hinzu, wenn nötig
                    }
                    navController.navigate(quizview + "/" + lecture.id)
                }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_lightbulb_24),
                    contentDescription = "Lecture",
                    tint = White,
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = "0" + lecture.id + " " + lecture.title,
                    style = Typography.headlineSmall,
                    color = White
                )
            }
        }
    }
}


/**
 * Composable-Funktion zur Darstellung eines gesperrten Quizzes innerhalb der Quizzesübersicht.
 *
 * Diese Funktion zeigt eine nicht-klickbare Karte für ein gesperrtes Quiz.
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LockedQuizzesItem() {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(3.25f)
            .clip(RoundedCornerShape(10.dp))
            .background(LightMidGrey)
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterStart)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_lock_24),
                    contentDescription = "Locked",
                    tint = White,
                    modifier = Modifier.size(35.dp)
                )

                Text(
                    text = "Gesperrt",
                    style = Typography.titleLarge,
                    color = White
                )

            }
        }
    }
}
