package de.hsd.modulearn.screens.module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.*

/**
 * Composable Funktion, die die Ansicht für ein Kapitel innerhalb einer Lektion darstellt.
 *
 * @param navController Der [NavController], der für die Navigation verwendet wird.
 * @param title Der Titel des anzuzeigenden Kapitels.
 * @param content Der Inhalt des anzuzeigenden Kapitels.
 */
@Composable
fun ChapterView(navController: NavController, title :String, content: String) {
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
            .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ){
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = title,
                    style = Typography.headlineSmall,
                    modifier = Modifier
                        .padding(15.dp)
                )

                Text(
                    text = content,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .padding(15.dp))
            }
        }
    }
}