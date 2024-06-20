package de.hsd.modulearn.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.hsd.modulearn.data.getChat

@Composable
fun ChatBotView(navController: NavController, question: String) {
    var response by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        response = getChat(question)
    }

    Box {
        Text(response ?: "Antwort des Chatbots laden...") // Anzeige während der Antwort geladen wird
    }
}