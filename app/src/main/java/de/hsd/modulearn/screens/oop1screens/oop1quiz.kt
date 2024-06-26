package de.hsd.modulearn.screens.oop1screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.oop1.Question
import de.hsd.modulearn.theme.*
import de.hsd.modulearn.utils.JsonReader
import androidx.compose.material3.LinearProgressIndicator
import de.hsd.modulearn.utils.AssetLoader

@Composable
fun Oop1Quiz(
    navController: NavController,
    id : Int,
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = { Header(title = "Quiz", backButton = true, navController = navController) },
        bottomBar = { Footer(navController = navController, selectedItemIndex = 2) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            val quiz = AssetLoader().getQuizById(id)

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                // Fortschrittsanzeige
                if (quiz != null) {
                    ProgressBar(
                        currentQuestionIndex = currentQuestionIndex,
                        totalQuestions = quiz.questions.size
                    )
                }

                val question = quiz?.questions?.get(currentQuestionIndex)

                if (question != null) {
                    Text(
                        text = question.questionText ?: "",
                        style = Typography.headlineSmall,
                        modifier = Modifier
                            .padding(PaddingValues(bottom = 15.dp))
                    )
                }

                if (question != null) {
                    question.answerOptions?.forEachIndexed { index, answer ->
                        AnswerOption(
                            answer = answer,
                            isSelected = index == selectedAnswer,
                            onClick = { selectedAnswer = index }
                        )
                    }
                }

                Button(
                    onClick = {
                        // Aktion ausführen, z.B. Antwort speichern oder weiterleiten
                        if (selectedAnswer != -1) {
                            // Hier könnte die Antwort gespeichert oder verarbeitet werden

                            // Zur nächsten Frage gehen, wenn nicht die letzte Frage erreicht ist
                            if (quiz != null) {
                                if (currentQuestionIndex < quiz.questions.size - 1) {
                                    currentQuestionIndex++
                                    // Zurücksetzen der ausgewählten Antwort für die neue Frage
                                    selectedAnswer = -1
                                } else {
                                    // Hier könnte eine Aktion nach dem Abschluss des Quiz durchgeführt werden
                                    // Zum Beispiel Navigation zur Ergebnisseite
                                }
                            }
                        }
                    },
                    enabled = selectedAnswer != -1,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryDarkBlue,
                        contentColor = White,
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    if (quiz != null) {
                        if (currentQuestionIndex < quiz.questions.size - 1) {
                            Text(text = "Nächste Frage")
                        } else {
                            Text(text = "Quiz beenden")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressBar(
    currentQuestionIndex: Int,
    totalQuestions: Int
) {
    val progress = ((currentQuestionIndex + 1) / totalQuestions.toFloat())

    LinearProgressIndicator(
        progress = progress,
        color = PrimaryDarkBlue,
        trackColor = LightGrey,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    )
}


@Composable
fun AnswerOption(
    answer: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) PrimaryMidBlue else LightGrey

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(backgroundColor, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(text = answer)
    }
}
