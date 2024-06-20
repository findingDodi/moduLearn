package de.hsd.modulearn.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.theme.*

@Composable
fun Footer(
    navController: NavController
) {
    var selected = homescreen

    BottomAppBar(
        containerColor = White
    ) {
        IconButton(
            onClick = {
                selected = homescreen
                navController.navigate(homescreen) // Ensure correct route
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Rounded.Home,
                contentDescription = "Home",
                modifier = Modifier.size(26.dp),
                tint = if (selected === homescreen) DarkGrey else LightMidGrey
            )
        }
        IconButton(
            onClick = {
                selected = oop1home
                navController.navigate(oop1home) // Ensure correct route
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Rounded.List,
                contentDescription = "Lectures",
                modifier = Modifier.size(26.dp),
                tint = if (selected === oop1home) DarkGrey else LightMidGrey
            )
        }
        IconButton(
            onClick = {
                selected = progressscreen
                navController.navigate(progressscreen) // Ensure correct route
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Rounded.Settings,
                contentDescription = "Progress",
                modifier = Modifier.size(26.dp),
                tint = if (selected === progressscreen) DarkGrey else LightMidGrey
            )
        }
    }
}
