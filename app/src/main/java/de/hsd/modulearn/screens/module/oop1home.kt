package de.hsd.modulearn.screens.module


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
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
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.theme.*
import de.hsd.modulearn.utils.AssetLoader

@Composable
fun Oop1Home(navController: NavController) {
    Scaffold (

        topBar = { Header("OOP1", true, navController) },
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
            val lectureList = AssetLoader().fullLectureList

            Column (modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(
                    text = "Lektionenübersicht",
                    style = Typography.headlineSmall,
                    modifier = Modifier.padding(PaddingValues(bottom= 15.dp))
                )

                ButtonWithIcon(
                    iconId = R.drawable.round_alt_route_24,
                    backgroundcolor = LightGrey,
                    color = Black,
                    text = "Roadmap" ,
                    destinationRoute = Routes.oop1roadmap,
                    navController = navController,
                    modifier = Modifier
                )


                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                ) {
                    items(lectureList.size){
                        LectureItem(lecture = lectureList[it], navController)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LectureItem (
    lecture: Lecture,
    navController:NavController
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1.25f)
            .clip(RoundedCornerShape(5.dp))
            .background(PrimaryMidBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = lecture.title,
                style = Typography.titleSmall,
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
                        navController.navigate(Routes.oop1lektion + "/" + lecture.id + "/" + lecture.title)
                    }
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(5.dp))
                    .background(PrimaryDarkBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}
