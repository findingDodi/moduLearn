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
import de.hsd.modulearn.data.oop1.Oop1Lektion
import de.hsd.modulearn.theme.*

@Composable
fun Oop1Home( navController: NavController) {
    Box(modifier = Modifier
        .background(White)
        .fillMaxSize()
    ){
        Column {
            Header("OOP1", true,  navController)

            lecturesOverview(oop1Lektionen = listOf(
                Oop1Lektion(
                    title = "01 - Grundlagen",
                    description = "Hallo Test"
                ),
                Oop1Lektion(
                    title = "02 - Programmiersprachen",
                    description = "Hallo Test"
                ),
                Oop1Lektion(
                    title = "03 - Grundlagen",
                    description = "Hallo Test"
                ),
                Oop1Lektion(
                    title = "04 - Programmiersprachen",
                    description = "Hallo Test"
                ),
            ), navController)

        }
        // Footer at the bottom
        Footer(modifier = Modifier.align(Alignment.BottomCenter), navController)

        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 120.dp, end = 15.dp)){
            ButtonWithIcon(
                iconId = R.drawable.round_chat_bubble_24,
                backgroundcolor = PrimaryDarkBlue,
                color = White,
                text = "ChatBot",
                destinationRoute = Routes.chatBot,
                navController = navController
            )
        }
    }
}


@Composable
fun lecturesOverview(oop1Lektionen: List<Oop1Lektion>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()){

        Text(
            text = "Lektionenübersicht",
            style = Typography.headlineSmall,
            modifier = Modifier
                .padding(PaddingValues(top = 20.dp, start = 15.dp, end = 15.dp, bottom= 15.dp))
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start= 7.5.dp, end=7.5.dp, bottom = 100.dp),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(oop1Lektionen.size){
                Oop1LektionItem(lektion = oop1Lektionen[it], navController)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Oop1LektionItem (
    lektion: Oop1Lektion,
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
                text = lektion.title,
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
                        navController.navigate(Routes.oop1lektion + "/" + lektion.title)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(PrimaryDarkBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}
