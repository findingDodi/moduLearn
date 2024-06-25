package de.hsd.modulearn.screens.oop1screens


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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.*
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.oop1.Lecture
import de.hsd.modulearn.theme.*

@Composable
fun Oop1Home(navController: NavController) {
    Scaffold (

        topBar = { Header("OOP1", false, navController) },
        bottomBar = { Footer(navController,1) },
        floatingActionButton = {
            ButtonChatBot(navController = navController)
        }

    ) {innerPadding ->
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
        ){
            Column {
                LecturesOverview(oop1Lektionen = listOf(
                    Lecture(
                        title = "01 - Grundlagen",
                        description = "Hallo Test"
                    ),
                    Lecture(
                        title = "02 - Programmiersprachen",
                        description = "Hallo Test"
                    ),
                    Lecture(
                        title = "03 - Grundlagen",
                        description = "Hallo Test"
                    ),
                    Lecture(
                        title = "04 - Programmiersprachen",
                        description = "Hallo Test"
                    ),
                ), navController)

            }
        }
    }
}


@Composable
fun LecturesOverview(oop1Lektionen: List<Lecture>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            text = "Lektionenübersicht",
            style = Typography.headlineSmall,
            modifier = Modifier
                .padding(PaddingValues(bottom= 15.dp))
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(oop1Lektionen.size){
                Oop1LektionItem(lecture = oop1Lektionen[it], navController)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Oop1LektionItem (
    lecture: Lecture,
    navController:NavController
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryMidBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = lecture.title,
                style = Typography.headlineSmall,
                color = White,
                modifier = Modifier.align(Alignment.TopStart)
            )
            // Start Button
            Text(
                text = "Lektion starten",
                color = White,
                style = Typography.labelLarge,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Routes.oop1lektion + "/" + lecture.title)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(5.dp))
                    .background(PrimaryDarkBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}
