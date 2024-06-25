package de.hsd.modulearn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.White

@Composable
fun ProgressScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("Fortschritt", false, navController) },
        bottomBar = { Footer(navController, 3) }

    ) {innerPadding ->

        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                ButtonChatBot(navController)
            }

        }

    }
}