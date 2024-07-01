package de.hsd.modulearn.screens.module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.theme.*

@Composable
fun QuizResultView(
    navController: NavController,
    correctAnswers: Int,
    totalQuestions: Int,
    id : Int,
) {
    val passPercentage = 0.7
    val isPassed = correctAnswers >= (totalQuestions * passPercentage)

    Scaffold(
        topBar = { Header(title = "Ergebnis", backButton = true, navController = navController) },
        bottomBar = { Footer(navController = navController, selectedItemIndex = 2) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(innerPadding)
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
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Du hast $correctAnswers von $totalQuestions Fragen richtig beantwortet.",
                    style = Typography.bodyLarge
                )
                if (isPassed) {
                    ButtonWithIcon(
                        iconId = R.drawable.round_arrow_forward_24,
                        backgroundcolor = PrimaryDarkLilac,
                        color = White,
                        text = "Quiz erfolgreich abgeschlossen",
                        destinationRoute = Routes.moduleview,
                        navController = navController,
                        modifier = Modifier
                    )
                } else {
                    ButtonWithIcon(
                        iconId = R.drawable.round_arrow_forward_24,
                        backgroundcolor = PrimaryDarkLilac,
                        color = White,
                        text = "Quiz neu Starten",
                        destinationRoute = Routes.quizview + "/" + id,
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
