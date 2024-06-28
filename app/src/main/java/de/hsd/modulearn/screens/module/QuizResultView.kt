package de.hsd.modulearn.screens.module

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import androidx.compose.ui.Modifier
import de.hsd.modulearn.theme.*

@Composable
fun QuizResultScreen(
    navController: NavController,
    correctAnswers: Int,
    totalQuestions: Int,
) {
    Scaffold(
        topBar = { Header(title = "Ergebnis", backButton = true, navController = navController) },
        bottomBar = { Footer(navController = navController, selectedItemIndex = 2) }
    ) {
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Quiz abgeschlossen!",
                    style = Typography.headlineSmall,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Du hast $correctAnswers von $totalQuestions Fragen richtig beantwortet.",
                    style = Typography.bodyLarge
                )
            }
        }
    }
}
