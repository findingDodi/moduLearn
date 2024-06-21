package de.hsd.modulearn.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.data.BottomMenuContent
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.data.Routes.oop1quiz
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.data.Routes.quizzesscreen
import de.hsd.modulearn.theme.*

@Composable
fun Footer(
    navController: NavController,
    selectedItemIndex: Int
) {
    val items = listOf(
        BottomMenuContent("Home", homescreen, R.drawable.round_home_24),
        BottomMenuContent("Lectures", oop1home, R.drawable.round_bookmarks_24),
        BottomMenuContent("Quizzes", quizzesscreen, R.drawable.round_lightbulb_24),
        BottomMenuContent("Process", progressscreen, R.drawable.round_bar_chart_24),
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
