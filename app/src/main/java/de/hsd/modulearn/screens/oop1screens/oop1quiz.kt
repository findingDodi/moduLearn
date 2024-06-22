package de.hsd.modulearn.screens.oop1screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.White

@Composable
fun Oop1Quiz(
    navController: NavController,
    title: String
) {
    Scaffold(
        topBar = { Header(title = title, navController = navController) },
        bottomBar = { Footer(navController = navController, selectedItemIndex = 3) }

    ) {innerPadding ->
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
        ) {
            Column {

            }
        }
    }
}