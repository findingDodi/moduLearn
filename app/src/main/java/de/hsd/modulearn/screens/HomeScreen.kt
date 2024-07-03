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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.components.InfoBox
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.roadmapview
import de.hsd.modulearn.theme.*

/**
 * Hauptbildschirm der Anwendung, der eine Liste von Modulen darstellt.
 *
 * Jedes Modul wird als Karte dargestellt, die den Titel des Moduls und ein Bild enthält.
 *
 * @param navController Der NavController für die Navigation innerhalb der Anwendung.
 */
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

                InfoBox()

                val modules = listOf(
                    Module(
                        title = "OOP1",
                        image = R.drawable.oop,
                        view = roadmapview
                    ),
                    Module(
                        title = "MCI",
                        image = R.drawable.mci,
                        view = homescreen
                    ),
                )

                modules.forEach { module ->
                    ModuleItem(module, navController)
                }
            }
        }
    }
}
/**
 * Composable Funktion zur Darstellung eines einzelnen Moduls als Karte.
 *
 * Die Karte enthält den Titel des Moduls
 * und ein Bild, das beim Klicken auf das Modul zu einem anderen Bildschirm navigiert.
 *
 * @param module Das Modulobjekt, das dargestellt werden soll.
 * @param navController Der NavController für die Navigation innerhalb der Anwendung.
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ModuleItem (
    module: Module,
    navController:NavController
) {
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
                    navController.navigate(module.view)
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