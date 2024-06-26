package de.hsd.modulearn.screens.oop1screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.oop1.*
import de.hsd.modulearn.theme.*

@Composable
fun Oop1LektionView(navController: NavController, title :String) {
    Scaffold (

        topBar = { Header("OOP1", true, navController) },
        bottomBar = { Footer(navController, 1) },
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
            Column (
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                Text(
                    text = title,
                    style = Typography.headlineSmall
                )

                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    ButtonWithIcon(
                        iconId = R.drawable.round_arrow_forward_24,
                        backgroundcolor = PrimaryDarkLilac,
                        color = White,
                        text = "Quiz starten",
                        destinationRoute = Routes.oop1quiz + "/Lektion_ID",
                        navController = navController,
                    )
                }

                ChaptersOverview(chapter = listOf(
                    Chapter(
                        title = "Compile and Run",
                        description = "Hallo Test",
                        content = "Test"
                    ),
                    Chapter(
                        title = "Warum Java?",
                        description = "Hallo Test",
                        content = "Test"
                    ),
                    Chapter(
                        title = "Was ist Programmieren?",
                        description = "Hallo Test",
                        content = "Test"
                    )
                ), navController)

            }
        }
    }

}

@Composable
fun ChaptersOverview(chapter: List<Chapter>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(chapter.size){
                ChapterItem(lektion = chapter[it], navController)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ChapterItem(
    lektion: Chapter,
    navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(5.dp)
            .aspectRatio(1.25f)
            .clip(RoundedCornerShape(5.dp))
            .background(PrimaryMidLilac)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = lektion.title,
                style = Typography.titleSmall,
                color = White,
                modifier = Modifier.align(Alignment.TopStart)
            )
            // Start Button
            Text(
                text = "Kapitel starten",
                color = White,
                style = Typography.labelLarge,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Routes.oop1kapitel + "/" + lektion.title)
                    }
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(5.dp))
                    .background(PrimaryDarkLilac)
                    .padding(vertical = 6.dp, horizontal = 26.dp)
            )
        }
    }
}
