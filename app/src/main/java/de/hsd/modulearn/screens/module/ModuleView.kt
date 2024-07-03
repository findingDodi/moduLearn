package de.hsd.modulearn.screens.module


import android.annotation.SuppressLint
import androidx.compose.animation.shrinkVertically
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
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.module.FinalQuiz
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.data.module.Quiz
import de.hsd.modulearn.theme.Black
import de.hsd.modulearn.theme.LightMidGrey
import de.hsd.modulearn.theme.PrimaryDarkLilac
import de.hsd.modulearn.theme.PrimaryMidBlue
import de.hsd.modulearn.theme.PrimaryMidLilac
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White
import de.hsd.modulearn.utils.AssetLoader

/**
 * Composable-Funktion zur Darstellung des Modulübersichts.
 *
 * Diese Funktion zeigt eine Liste von Lektionen und eine Option zum Starten der Abschlussprüfung.
 *
 * @param navController Der [NavController], der für die Navigation innerhalb der App verwendet wird.
 */
@Composable
fun ModuleView(navController: NavController) {
    // Zugriff auf den Context und die MainActivity für den Zugriff auf freigeschaltete Module
    val context = LocalContext.current
    val mainActivity = context as MainActivity
    val unlockedModules = mainActivity.getUnlockedModules()

    Scaffold (

        topBar = { Header("OOP1", true, navController) },
        bottomBar = { Footer(navController,1) },
        floatingActionButton = { ButtonChatBot(navController = navController) }

    ) { innerPadding ->
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
            .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            Column (
                modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val lectureList = AssetLoader().fullLectureList

                Text(
                    text = "Lektionenübersicht",
                    style = Typography.headlineMedium,
                )

                ButtonWithIcon(
                    iconId = R.drawable.round_map_24,
                    backgroundcolor = White,
                    color = Black,
                    text = "Roadmap" ,
                    destinationRoute = Routes.roadmapview,
                    navController = navController,
                    modifier = Modifier
                )

                // überprüft, ob der Index eines Moduls kleiner oder gleich unlockedModules ist
                lectureList.forEachIndexed { index, lecture ->
                    if (index + 1 <= unlockedModules) {
                        LectureItem(lecture, navController)
                    } else {
                        LockedItem()
                    }
                }

                FinalQuizItem (navController)
            }
        }
    }
}

/**
 * Composable-Funktion zur Darstellung einer Lektion innerhalb der Modulübersicht.
 *
 * Diese Funktion zeigt eine klickbare Karte für eine Lektion mit ihrem Titel und einem Symbol.
 *
 * @param lecture Das [Lecture]-Objekt, das die Lektion repräsentiert.
 * @param navController Der [NavController], der für die Navigation innerhalb der App verwendet wird.
 */

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LectureItem (
    lecture: Lecture,
    navController:NavController
) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(3.25f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryMidBlue)
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    navController.navigate(Routes.lectureview + "/" + lecture.id + "/" + lecture.title)
                }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_bookmarks_24),
                    contentDescription = "Lecture",
                    tint = White,
                    modifier = Modifier.size(35.dp)
                )

                Text(
                    text = lecture.title,
                    style = Typography.titleLarge,
                    color = White
                )
            }
        }
    }
}
/**
 * Composable-Funktion zur Darstellung der Abschlussprüfungsoption innerhalb der Lektionenübersicht.
 *
 * Diese Funktion zeigt eine klickbare Karte für den Start der Abschlussprüfung.
 *
 * @param navController Der [NavController], der für die Navigation innerhalb der App verwendet wird.
 */

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun FinalQuizItem (
    navController:NavController
) {
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
                    navController.navigate(Routes.finalQuizViewIntro)
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
                    text = "Abschlussprüfung",
                    style = Typography.titleLarge,
                    color = White
                )
            }
        }
    }
}

/**
 * Composable-Funktion zur Darstellung einer gesperrten Moduloption innerhalb der Modulübersicht.
 *
 * Diese Funktion zeigt eine klickbare Karte für ein gesperrtes Modul.
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LockedItem() {
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
