package de.hsd.modulearn.screens.module
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.Alignment
import de.hsd.modulearn.utils.QuizLogic
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.utils.AssetLoader
@Composable
fun Oop1Quiz(
    navController: NavController,
    id: Int,
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswers by remember { mutableStateOf<MutableList<Int>>(mutableListOf()) }
    var feedbackMessage by remember { mutableStateOf("") }
    var showNextButton by remember { mutableStateOf(false) }
    var answerColors by remember { mutableStateOf<List<Color>>(emptyList()) }
    var correctAnswers by remember { mutableStateOf(0) }

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
            val quizLogic = QuizLogic()

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

                    val questionTypeText = when (question.type) {
                        "SINGLE_CHOICE" -> "Wähle eine Antwort aus:"
                        "MULTIPLE_CHOICE" -> "Wähle alle zutreffenden Antworten aus:"
                        else -> ""
                    }

                    if (questionTypeText.isNotEmpty()) {
                        Text(
                            text = questionTypeText,
                            style = Typography.bodyMedium,
                            modifier = Modifier
                                .padding(PaddingValues(bottom = 10.dp))
                        )
                    }
                }

                if (question != null) {
                    if (answerColors.isEmpty()) {
                        answerColors = List(question.answerOptions!!.size) { LightGrey }
                    }
                    question.answerOptions?.forEachIndexed { index, answer ->
                        AnswerOption(
                            answer = answer,
                            isSelected = index in selectedAnswers,
                            color = answerColors.getOrElse(index) { LightGrey },
                            onClick = {
                                if (question.type == "SINGLE_CHOICE") {
                                    selectedAnswers.clear()
                                    selectedAnswers.add(index)
                                } else if (question.type == "MULTIPLE_CHOICE") {
                                    if (index in selectedAnswers) {
                                        selectedAnswers.remove(index)
                                    } else {
                                        selectedAnswers.add(index)
                                    }
                                }
                                answerColors = answerColors.mapIndexed { i, _ ->
                                    if (i == index) PrimaryDarkBlue else LightGrey
                                }
                            }
                        )
                    }
                }

                // Feedback anzeigen
                if (feedbackMessage.isNotEmpty()) {
                    Text(
                        text = feedbackMessage,
                        style = Typography.bodyLarge,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(
                                if (quizLogic.areMultipleChoiceAnswersCorrect(
                                        selectedAnswers,
                                        question?.answer
                                    )
                                ) SecondaryGreen else SecondaryRed,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(16.dp)
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth() ,
                    contentAlignment = Alignment.Center
                ) {
                    val buttonText = if (currentQuestionIndex < quiz!!.questions.size - 1) {
                        "Zur nächsten Frage"
                    } else {
                        "Quiz abschließen"
                    }

                    if (showNextButton) {
                        Button(
                            onClick = {
                                // Zur nächsten Frage gehen, wenn nicht die letzte Frage erreicht ist
                                if (quiz != null) {
                                    if (currentQuestionIndex < quiz.questions.size - 1) {
                                        currentQuestionIndex++
                                        // Zurücksetzen der ausgewählten Antwort und des Feedbacks für die neue Frage
                                        selectedAnswers.clear()
                                        feedbackMessage = ""
                                        showNextButton = false
                                        answerColors = List(quiz.questions[currentQuestionIndex].answerOptions!!.size) { LightGrey }
                                    } else {
                                        navController.navigate(Routes.oop1quizresultview + "/" + correctAnswers + "/"+ quiz.questions.size)
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryDarkBlue,
                                contentColor = White,
                            ),
                            modifier = Modifier
                                .padding(top = 20.dp)
                        ) {
                            Text(text = buttonText)
                        }
                    } else {
                        Button(
                            onClick = {
                                // Überprüfung der Antwort
                                if (selectedAnswers.isNotEmpty()) {
                                    if (question != null) {
                                        val isCorrect = quizLogic.areMultipleChoiceAnswersCorrect(selectedAnswers, question.answer)
                                        feedbackMessage = if (isCorrect) {
                                            correctAnswers++ // Erhöhe den Zähler für korrekte Antworten
                                            "Richtig!"
                                        } else {
                                            "Falsch. ${question.explanation ?: ""}"
                                        }
                                        showNextButton = true
                                        answerColors = question.answerOptions!!.mapIndexed { index, _ ->
                                            when {
                                                index in selectedAnswers && index in (question.answer ?: emptyList()) -> SecondaryGreen
                                                index in selectedAnswers && index !in (question.answer ?: emptyList()) -> SecondaryRed
                                                else -> LightGrey
                                            }
                                        }
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryDarkBlue,
                                contentColor = White,
                            ),
                            modifier = Modifier
                                .padding(top = 20.dp)
                        ) {
                            Text(text = "Antwort senden")
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
    color: Color,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) PrimaryLightBlue else color

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
