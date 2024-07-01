package de.hsd.modulearn.screens.module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.ImageLoader
import coil.decode.SvgDecoder

import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.components.RoadmapButton
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.theme.*
import de.hsd.modulearn.utils.AssetLoader

@Composable
fun RoadmapView(navController: NavController) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Scaffold(

        topBar = { Header("OOP1", false, navController) },
        bottomBar = { Footer(navController, 1) }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(SecondaryGreenLight)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    ButtonWithIcon(
                        iconId = R.drawable.round_format_list_bulleted_24,
                        backgroundcolor = White,
                        color = Black,
                        text = "Liste" ,
                        destinationRoute = Routes.moduleview,
                        navController = navController,
                        modifier = Modifier
                    )
                }

                AsyncImage(
                    model = "file:///android_asset/Roadmap.svg",
                    contentDescription = "SVG Image",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                val lectureList = AssetLoader().fullLectureList

                val positions = mapOf(
                    1 to Pair(250.dp, 488.dp),
                    2 to Pair(120.dp, 505.dp),
                    3 to Pair(45.dp, 440.dp),
                    4 to Pair(110.dp, 370.dp),
                    5 to Pair(200.dp, 345.dp),
                    6 to Pair(225.dp, 250.dp),
                    7 to Pair(160.dp, 185.dp),
                    8 to Pair(72.dp, 120.dp),
                    9 to Pair(180.dp, 9.dp)
                )


                Box(modifier = Modifier.fillMaxSize()) {

                    lectureList.forEach { lecture ->

                        val position = positions[lecture.id] ?: return@forEach

                        RoadmapButton(
                            text = lecture.id.toString(),
                            destinationRoute = Routes.lectureview + "/" + lecture.id + "/" + lecture.title,
                            navController = navController,
                            modifier = Modifier
                                .absoluteOffset(x = position.first, y = position.second)
                        )
                    }
                }
            }
        }
    }
}
