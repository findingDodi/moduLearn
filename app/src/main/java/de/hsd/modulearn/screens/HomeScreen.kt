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
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes.moduleview
import de.hsd.modulearn.theme.PrimaryDarkBlue
import de.hsd.modulearn.theme.PrimaryMidBlue
import de.hsd.modulearn.theme.PrimaryMidLilac
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("moduLearn", false, navController) },
        bottomBar = { Footer(navController, 0) },
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

                ModuleOverview(
                    modules = listOf(
                        Module(
                            title = "OOP1",
                            moduleColor = PrimaryMidBlue
                        ),
                        Module(
                            title = "MCI",
                            moduleColor = PrimaryMidLilac
                        ),
                    ), navController
                )

            }
        }
    }
}



@Composable
fun ModuleOverview(modules: List<Module>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()){

        Text(
            text = "Modulübersicht",
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
                ModuleItem(module = modules[it], navController)
            }
        }

    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ModuleItem (
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
            .clip(RoundedCornerShape(5.dp))
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
                        navController.navigate(moduleview)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(5.dp))
                    .background(PrimaryDarkBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }


    }
}