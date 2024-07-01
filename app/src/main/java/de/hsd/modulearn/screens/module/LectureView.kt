package de.hsd.modulearn.screens.module


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.module.Chapter
import de.hsd.modulearn.theme.*
import de.hsd.modulearn.utils.AssetLoader

@Composable
fun LectureView(navController: NavController, id : Int, title :String) {
    Scaffold (

        topBar = { Header("OOP1", true, navController) },
        bottomBar = { Footer(navController, 1) },
        floatingActionButton = {
            ButtonChatBot(navController = navController)
        }

    ) { innerPadding ->


        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
            .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                val chapterList = AssetLoader().getChaptersFromLectureById(id)

                if (title == ""){

                    val lecturetitle = AssetLoader().getTitleFromLectureById(id).toString()
                    Text(
                        text = lecturetitle,
                        style = Typography.headlineMedium
                    )

                } else{

                    Text(
                        text = title,
                        style = Typography.headlineMedium
                    )

                }



                ButtonWithIcon(
                    iconId = R.drawable.round_lightbulb_24,
                    backgroundcolor = PrimaryDarkLilac,
                    color = White,
                    text = "Quiz starten",
                    destinationRoute = Routes.quizview + "/" + id,
                    navController = navController,
                    modifier = Modifier
                )

                chapterList?.forEach { chapter ->
                    ChapterItem(chapter, navController)
                }
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ChapterItem(
    chapter: Chapter,
    navController: NavController
) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(3.25f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryLightBlue)
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    navController.navigate(Routes.chapterview + "/" + chapter.title + "/" + chapter.content)
                }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_library_books_24),
                    contentDescription = "Lecture",
                    tint = PrimaryDarkBlue,
                    modifier = Modifier.size(35.dp)
                )

                Text(
                    text = chapter.title,
                    style = Typography.titleLarge,
                    color = PrimaryDarkBlue
                )
            }
        }
    }
}
