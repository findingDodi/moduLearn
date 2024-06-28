package de.hsd.modulearn.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.theme.*

@Composable
fun ButtonChatBot(navController: NavController) {
    ButtonWithIcon(
        iconId = R.drawable.round_chat_bubble_24,
        backgroundcolor = White,
        color = DarkGrey,
        text = "ChatBot",
        destinationRoute = Routes.chatbotview,
        navController = navController,
        modifier = Modifier
    )
}