package de.hsd.modulearn.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.data.BottomMenuContent
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.theme.*
import io.ktor.websocket.Frame

@Composable
fun Footer(
    navController: NavController
) {
    val items = listOf(
        BottomMenuContent("Home", homescreen, Icons.Rounded.Home),
        BottomMenuContent("Lectures", oop1home, Icons.Rounded.List),
        BottomMenuContent("Process", progressscreen, Icons.Rounded.Settings),
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        containerColor = White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.screen)
                          },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(26.dp),
                        tint = if (index == selectedItemIndex) {
                            DarkGrey
                        } else {
                            LightMidGrey
                        }
                    )
                })
        }
    }
}
