package de.hsd.modulearn.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.MainActivity
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes.moduleview
import de.hsd.modulearn.data.Routes.roadmapview
import de.hsd.modulearn.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("moduLearn", false, navController) },
        bottomBar = { Footer(navController, 0) },
        floatingActionButton = { ButtonChatBot(navController = navController) }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                val modules = listOf(
                    Module(
                        title = "OOP1",
                        image = R.drawable.oop
                    ),
                    Module(
                        title = "MCI",
                        image = R.drawable.mci
                    ),
                )

                modules.forEach { module ->
                    ModuleItem(module, navController)
                }
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ModuleItem (
    module: Module,
    navController:NavController
) {
    //Für die Punkte: Kontext und MainActivity-Instanz abrufen
    val context = LocalContext.current
    val mainActivity = context as MainActivity

    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(2f)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryDarkBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .clickable {
                    //Für die Punkte: Punkte basierend auf dem Modultitel erhöhen
                    when (module.title) {
                        "OOP1" -> mainActivity.setPoints(mainActivity.getPoints() + 20)
                        "MCI" -> mainActivity.setPoints(mainActivity.getPoints() + 55)
                    }
                    navController.navigate(roadmapview)
                }
        ) {
            Row {
                Image(
                    painter = painterResource(module.image),
                    contentDescription = module.title
                )

            }
            Text(
                text = module.title,
                style = Typography.headlineLarge,
                color = White,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}