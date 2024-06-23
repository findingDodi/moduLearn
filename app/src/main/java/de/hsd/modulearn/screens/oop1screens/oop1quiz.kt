package de.hsd.modulearn.screens.oop1screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.LightGrey
import de.hsd.modulearn.theme.PrimaryDarkBlue
import de.hsd.modulearn.theme.PrimaryMidBlue
import de.hsd.modulearn.theme.White

@Composable
fun Oop1Quiz(
    navController: NavController,
    title: String
) {
    var selectedAnswer by remember { mutableStateOf(-1) }
    val question = "Was ist ein Interface in der objektorientierten Programmierung?"
    val answers = listOf(
        "Eine Klasse, die Objekte erzeugt",
        "Eine Methode, die von mehreren Klassen verwendet werden kann",
        "Ein Vertrag, den Klassen erfüllen müssen",
        "Eine Datenstruktur zur Speicherung von Objekten"
    )

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = question,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                answers.forEachIndexed { index, answer ->
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
                            println("Ausgewählte Antwort: ${answers[selectedAnswer]}")
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
