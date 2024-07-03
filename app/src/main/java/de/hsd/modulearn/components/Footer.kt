package de.hsd.modulearn.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import de.hsd.modulearn.R
import de.hsd.modulearn.data.BottomMenuContent
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.roadmapview
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.data.Routes.quizzesscreen
import de.hsd.modulearn.theme.*

/**
 * Eine Composable Funktion, die eine Fußzeilen-Navigationsleiste mit auswählbaren Elementen anzeigt.
 *
 * @param navController Der [NavController], der für die Navigation zwischen den Zielen verwendet wird.
 * @param selectedItemIndex Der Index des aktuell ausgewählten Elements in der Navigationsleiste.
 */
@Composable
fun Footer(
    navController: NavController,
    selectedItemIndex: Int
) {
    val items = listOf(
        BottomMenuContent("Home", homescreen, R.drawable.round_home_24),
        BottomMenuContent("Lektionen", roadmapview, R.drawable.round_bookmarks_24),
        BottomMenuContent("Quizzes", quizzesscreen, R.drawable.round_lightbulb_24),
        BottomMenuContent("Erfolge", progressscreen, R.drawable.round_bar_chart_24),
    )

    NavigationBar(
        containerColor = White
    ) {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    navController.navigate(item.screen)
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
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
