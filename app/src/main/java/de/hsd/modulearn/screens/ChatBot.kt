package de.hsd.modulearn.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.getChat
import de.hsd.modulearn.theme.Black
import de.hsd.modulearn.theme.PrimaryDarkBlue
import de.hsd.modulearn.theme.PrimaryLightBlue
import de.hsd.modulearn.theme.PrimaryMidBlue
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White
import kotlinx.coroutines.launch

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
                        containerColor = PrimaryLightBlue,
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
