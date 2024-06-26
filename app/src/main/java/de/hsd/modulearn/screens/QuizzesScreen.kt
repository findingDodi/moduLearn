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
import de.hsd.modulearn.R
import de.hsd.modulearn.components.*
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.theme.*

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

                QuizzesOverview(
                    modules = listOf(
                        Module(
                            title = "Quiz 1",
                            moduleColor = PrimaryMidBlue
                        ),
                        Module(
                            title = "Quiz 1",
                            moduleColor = PrimaryMidBlue
                        ),
                    ), navController
                )

            }
        }
    }
}

@Composable
fun QuizzesOverview(modules: List<Module>, navController:NavController) {
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
            items(modules.size){
                QuizzesItem(module = modules[it], navController)
            }
        }

    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun QuizzesItem (
    module: Module,
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
            .background(module.moduleColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = module.title,
                style = Typography.headlineSmall,
                color = White,
                modifier = Modifier.align(Alignment.TopStart)
            )
            // Start Button
            Text(
                text = "Start",
                color = White,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .clickable {
                        //Für die Punkte: Punkte basierend auf dem Modultitel erhöhen
                        when (module.title) {
                            "OOP1" -> mainActivity.setPoints(mainActivity.getPoints() + 20)
                            "MCI" -> mainActivity.setPoints(mainActivity.getPoints() + 55)
                            // Fügen Sie hier weitere Module und entsprechende Punkte hinzu, wenn nötig
                        }
                        navController.navigate(oop1home)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(PrimaryDarkBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }


    }
}