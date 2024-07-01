package de.hsd.modulearn.screens.module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.logic.getKiEvaluation
import de.hsd.modulearn.theme.Black
import de.hsd.modulearn.theme.LightGrey
import de.hsd.modulearn.theme.PrimaryDarkBlue
import de.hsd.modulearn.theme.PrimaryLightBlue
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White
import de.hsd.modulearn.utils.AssetLoader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalQuizViewStart(navController: NavController) {
    Scaffold(
        topBar = { Header("OOP1", true, navController) },
        bottomBar = { Footer(navController, 1) },
        floatingActionButton = {
            ButtonChatBot(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight()
            ) {

                val finalQuiz = AssetLoader().finalQuiz
                val questions = finalQuiz.questions

                var currentQuestionIndex by remember { mutableStateOf(0) }
                var answerText by remember { mutableStateOf("") }
                var feedbackTextVisible by remember { mutableStateOf(false) }
                var kiFeedback by remember { mutableStateOf<String?>(null) }
                val coroutineScope = rememberCoroutineScope()

                if (questions.isNotEmpty()) {
                    Text(
                        text = questions[currentQuestionIndex].questionText,
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(15.dp)
                    )

                    if (!feedbackTextVisible) {
                        TextField(
                            value = answerText,
                            onValueChange = { answerText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                                .height(300.dp),
                            placeholder = { Text(text = "Ihre Antwort hier eingeben...") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = LightGrey,
                                focusedLabelColor = Black,
                                cursorColor = Black,
                                focusedIndicatorColor = PrimaryDarkBlue
                            )
                        )

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryDarkBlue,
                                contentColor = White,
                            ),
                            onClick = {
                                coroutineScope.launch {
                                    kiFeedback = getKiEvaluation(answerText) ?: "Keine Antwort erhalten"
                                }
                                feedbackTextVisible = true
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Antwort absenden")
                        }
                    } else {
                        Text(
                            text = kiFeedback ?: "Antwort des Chatbots laden...",
                            style = Typography.bodyLarge,
                            modifier = Modifier
                                .padding(15.dp)
                        )

                        if (currentQuestionIndex < questions.size - 1) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryDarkBlue,
                                    contentColor = White,
                                ),
                                onClick = {
                                    feedbackTextVisible = false
                                    currentQuestionIndex++
                                    answerText = ""
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "Nächste Frage")
                            }
                        } else {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryDarkBlue,
                                    contentColor = White,
                                ),
                                onClick = {
                                    navController.navigate(Routes.moduleview)
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "Zurück zur Übersicht")
                            }
                        }
                    }
                }
            }
        }
    }
}
