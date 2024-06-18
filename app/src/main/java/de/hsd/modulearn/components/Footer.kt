package de.hsd.modulearn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.data.BottomMenuContent
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.theme.*

@Composable
fun Footer(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    BottomMenu(
        items = listOf(
            BottomMenuContent("Home", homescreen, R.drawable.round_account_balance_24),
            BottomMenuContent("Lektionen", oop1home, R.drawable.round_bookmarks_24),
            BottomMenuContent("Fortschritt", progressscreen, R.drawable.round_bar_chart_24),
        ),
        modifier = modifier,
        navController
    )
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    navController: NavController,
    activeHighlightColor: Color = PrimaryDarkBlue,
    activeTextColor: Color = White,
    inactiveTextColor: Color = PrimaryDarkBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(10.dp)
            .fillMaxWidth()
            .background(White)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                navController,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}


@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    navController: NavController,
    isSelected: Boolean = false,
    activeHighlightColor: Color = PrimaryDarkBlue,
    activeTextColor: Color = White,
    inactiveTextColor: Color = PrimaryDarkBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            navController.navigate(item.screen)
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = inactiveTextColor
        )
    }
}