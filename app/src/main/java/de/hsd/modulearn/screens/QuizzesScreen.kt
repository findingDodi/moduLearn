package de.hsd.modulearn.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import de.hsd.modulearn.components.*
import de.hsd.modulearn.data.Routes.quizview
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.screens.module.LectureItem
import de.hsd.modulearn.theme.*
import de.hsd.modulearn.utils.AssetLoader

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
                    QuizzesItem(lecture, navController)
                }
            }
        }
    }
}

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
            .background(PrimaryMidBlue)
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