package de.hsd.modulearn.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.hsd.modulearn.ui.theme.Black
import de.hsd.modulearn.ui.theme.White

@Composable
fun Footer(navController: NavController) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = White, // Hintergrundfarbe
        contentColor = Black // Textfarbe
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("HomeScreen") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            IconButton(onClick = { navController.navigate("OOP1LectureScreen") }) {
                Icon(Icons.Filled.List, contentDescription = "Lectures")
            }
            IconButton(onClick = { navController.navigate("ProgressScreen") }) {
                Icon(Icons.Filled.Settings, contentDescription = "Progress")
            }
        }
    }
}