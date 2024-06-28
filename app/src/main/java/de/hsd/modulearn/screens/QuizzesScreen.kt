package de.hsd.modulearn.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.MainActivity
import de.hsd.modulearn.components.*
import de.hsd.modulearn.data.Routes.oop1quiz
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.theme.*
import de.hsd.modulearn.utils.AssetLoader

@Composable
fun QuizzesScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("Quizzes", false, navController) },
        bottomBar = { Footer(navController, 2) },
        floatingActionButton = {
            ButtonChatBot(navController = navController)
        }

    ) {innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val lectureList = AssetLoader().fullLectureList
                QuizzesOverview(lectureList, navController)

            }
        }
    }
}

@Composable
fun QuizzesOverview(lectures: List<Lecture>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()
    ){

        Text(
            text = "Quizzesübersicht",
            style = Typography.headlineSmall,
            modifier = Modifier
                .padding(PaddingValues(bottom= 15.dp))
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(start= 7.5.dp, end=7.5.dp, bottom = 100.dp),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(lectures.size){
                QuizzesItem(lecture = lectures[it], navController)
            }
        }

    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun QuizzesItem (
    lecture: Lecture,
    navController:NavController
){

    //Für die Punkte: Kontext und MainActivity-Instanz abrufen
    val context = LocalContext.current
    val mainActivity = context as MainActivity

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(2f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryMidBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Text(
                    text = "Quiz 0" + lecture.id,
                    style = Typography.displayMedium,
                    color = White
                )
                Text(
                    text = lecture.title,
                    style = Typography.headlineSmall,
                    color = White
                )
            }
            // Start Button
            Text(
                text = "Start",
                color = White,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .clickable {
                        //Für die Punkte: Punkte basierend auf dem Modultitel erhöhen
                        when (lecture.title) {
                            "OOP1" -> mainActivity.setPoints(mainActivity.getPoints() + 20)
                            "MCI" -> mainActivity.setPoints(mainActivity.getPoints() + 55)
                            // Fügen Sie hier weitere Module und entsprechende Punkte hinzu, wenn nötig
                        }
                        navController.navigate(oop1quiz + "/" + lecture.id)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(PrimaryDarkBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }


    }
}