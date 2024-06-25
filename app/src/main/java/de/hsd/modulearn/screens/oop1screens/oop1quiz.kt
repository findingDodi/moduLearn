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

@Composable
fun Oop1Quiz(
    navController: NavController,
    title: String
) {
    var selectedAnswer by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = { Header(title = title, navController = navController) },
        bottomBar = { Footer(navController = navController, selectedItemIndex = 2) }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            val jsonData = JsonReader().loadQuizFromJson(LocalContext.current)

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {


                jsonData.questions.forEach { question: Question ->

                    Text(text = question.questionText,
                        style = Typography.displaySmall,
                        modifier = Modifier
                            .padding(PaddingValues(bottom= 15.dp))
                    )

                    question.answerOptions.forEachIndexed { index, answer ->
                        AnswerOption(
                            answer = answer,
                            isSelected = index == selectedAnswer,
                            onClick = { selectedAnswer = index }
                        )
                    }

                    Button(
                        onClick = {
                            // Aktion ausführen, z.B. Antwort speichern oder weiterleiten
                            if (selectedAnswer != -1) {
                                // Beispiel: Antwort weiterleiten
                                //println("Ausgewählte Antwort: ${answers[selectedAnswer]}")
                            }
                        },
                        enabled = selectedAnswer != -1,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryDarkBlue,
                            contentColor = White,
                        ),
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        Text(text = "Antwort senden")
                    }
                }

            }
        }
    }
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
