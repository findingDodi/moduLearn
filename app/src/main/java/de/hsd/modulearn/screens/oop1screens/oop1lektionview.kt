package de.hsd.modulearn.screens.oop1screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.oop1.Oop1Kapitel
import de.hsd.modulearn.theme.*

@Composable
fun Oop1LektionView( navController: NavController, title :String) {
    Box(modifier = Modifier
        .background(White)
        .fillMaxSize()
    ){
        Column {
            Header("OOP1", true,  navController)
            Text(
                text = title,
                style = Typography.headlineMedium,
                modifier = Modifier
                    .padding(15.dp)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(15.dp)
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

            chaptersOverview(oop1Kapitel = listOf(
                Oop1Kapitel(
                    title = "Compile and Run",
                    description = "Hallo Test"
                ),
                Oop1Kapitel(
                    title = "Warum Java?",
                    description = "Hallo Test"
                ),
                Oop1Kapitel(
                    title = "Was ist Programmieren?",
                    description = "Hallo Test"
                )
            ), navController)

        }
        Footer(modifier = Modifier.align(Alignment.BottomCenter), navController)
    }
}

@Composable
fun chaptersOverview(oop1Kapitel: List<Oop1Kapitel>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()){

        Text(
            text = "Kapitelübersicht",
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
            items(oop1Kapitel.size){
                Oop1KapitelItem(lektion = oop1Kapitel[it], navController)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Oop1KapitelItem(
    lektion: Oop1Kapitel,
    navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryMidLilac)
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
                text = "Kapitel starten",
                color = White,
                style = Typography.labelLarge,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Routes.oop1kapitel + "/" + lektion.title)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(PrimaryDarkLilac)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}
