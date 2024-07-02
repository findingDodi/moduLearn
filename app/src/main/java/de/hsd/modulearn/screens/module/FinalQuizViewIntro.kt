package de.hsd.modulearn.screens.module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.theme.PrimaryDarkLilac
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White

@Composable
fun FinalQuizViewIntro(navController: NavController) {
    Scaffold (

        topBar = { Header("OOP1", true, navController) },
        bottomBar = { Footer(navController,1) },
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
        ){
            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight()
            ){
                Text(
                    text = "Abschlussprüfung",
                    style = Typography.headlineLarge,
                    modifier = Modifier
                        .padding(15.dp)
                )

                Text(
                    text = "Willkommen zur Abschlussprüfung des Moduls \"Objektorientierte Programmierung\". Diese Prüfung testet Ihr Verständnis der wesentlichen Konzepte wie Klassen, Objekte, Vererbung, Polymorphismus und Schnittstellen.\n" +
                            "\n" +
                            "Bitte beantworte die Fragen sorgfältig und vollständig. Viel Erfolg!",
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .padding(15.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ButtonWithIcon(
                        iconId = R.drawable.round_arrow_forward_24,
                        backgroundcolor = PrimaryDarkLilac,
                        color = White,
                        text = "Abschlussprüfung starten",
                        destinationRoute = Routes.finalQuizViewStart,
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}