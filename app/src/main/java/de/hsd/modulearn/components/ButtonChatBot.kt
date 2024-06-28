package de.hsd.modulearn.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.theme.*

@Composable
fun ButtonChatBot(navController: NavController) {
    ButtonWithIcon(
        iconId = R.drawable.round_chat_bubble_24,
        backgroundcolor = PrimaryDarkBlue,
        color = White,
        text = "ChatBot",
        destinationRoute = Routes.chatBot,
        navController = navController,
        modifier = Modifier
    )
}