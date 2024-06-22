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
import androidx.compose.material3.Scaffold
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
fun Oop1LektionView(navController: NavController, title :String) {
    Scaffold (

        topBar = { Header("OOP1", false, navController) },
        bottomBar = { Footer(navController, 1) }

    ) {innerPadding ->
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
        ){
            Column {
                Text(
                    text = title,
                    style = Typography.headlineMedium,
                    modifier = Modifier
                        .padding(15.dp)
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

                ChaptersOverview(oop1Kapitel = listOf(
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
        }
    }

}

@Composable
fun ChaptersOverview(oop1Kapitel: List<Oop1Kapitel>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
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
            .padding(5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryMidLilac)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
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
                    .clip(RoundedCornerShape(5.dp))
                    .background(PrimaryDarkLilac)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}
