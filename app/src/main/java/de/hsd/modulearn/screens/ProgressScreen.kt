package de.hsd.modulearn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("moduLearn", false, navController) },
        bottomBar = { Footer(navController) }

    ) {innerPadding ->
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp) // Optional: Platz für den Footer schaffen
            ) {

                Box (
                    modifier = Modifier
                        .padding(15.dp)
                ){
                    ButtonWithIcon(
                        iconId = R.drawable.round_chat_bubble_24,
                        backgroundcolor = PrimaryDarkBlue ,
                        color = White ,
                        text = "ChatBot" ,
                        destinationRoute = Routes.chatBot,
                        navController = navController
                    )
                }
            }
        }
    }
}