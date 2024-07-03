package de.hsd.modulearn.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.logic.getChat
import de.hsd.modulearn.theme.*
import kotlinx.coroutines.launch

/**
 * Composable zur Darstellung eines Chatbots für interaktive Fragen und Antworten.
 *
 * Diese Funktion stellt eine Benutzeroberfläche für einen Chatbot dar, der Fragen entgegennimmt und
 * Antworten zurückgibt. Benutzer können Fragen eingeben und die Antworten des Chatbots anzeigen lassen.
 *
 * @param navController Der NavController für die Navigation innerhalb der Anwendung.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBotView(navController: NavController) {

    var userInput by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
        ){
            Header(title = "ChatBot", navController = navController)
        }
            Column(
                modifier = Modifier
                    .padding(top=120.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                var question by remember { mutableStateOf("") }
                var response by remember { mutableStateOf<String?>(null) }
                val coroutineScope = rememberCoroutineScope()

                TextField(
                    value = question,
                    onValueChange = { question = it },
                    label = { Text("Frage eingeben") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = LightGrey,
                        focusedLabelColor = Black,
                        cursorColor = Black,
                        focusedIndicatorColor = PrimaryDarkBlue,
                    ),
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            response = getChat(question) ?: "Keine Antwort erhalten"
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryDarkBlue)
                ) {
                    Text(text = "Frage senden")
                }

                Box(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        text = response ?: "Antwort des Chatbots laden...",
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }

        }
    }
}
