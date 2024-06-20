package de.hsd.modulearn.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import de.hsd.modulearn.data.getChat
import de.hsd.modulearn.theme.PrimaryLightBlue
import de.hsd.modulearn.theme.PrimaryMidBlue
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
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
                    .fillMaxWidth()
                    .padding(16.dp),
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
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(PrimaryMidBlue)
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            response = getChat(question) ?: "Keine Antwort erhalten"
                        }
                    },
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
