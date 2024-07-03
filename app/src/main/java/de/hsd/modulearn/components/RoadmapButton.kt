package de.hsd.modulearn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.theme.*

/**
 * Eine Composable Funktion, die einen kreisförmigen Button erstellt, um zu einer angegebenen Zielseite zu navigieren.
 *
 * @param text Der Text, der auf dem Button angezeigt werden soll.
 * @param destinationRoute Die Route, zu der navigiert werden soll, wenn der Button geklickt wird.
 * @param navController Der [NavController], der für die Navigation verwendet wird.
 * @param modifier Optionaler [Modifier], um das Erscheinungsbild und Verhalten des Buttons anzupassen.
 */
@Composable
fun RoadmapButton(
    text: String,
    destinationRoute: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(PrimaryMidLilac)
            .clickable {
                navController.navigate(destinationRoute)
            }
    ) {
        Text(
            text = text,
            color = White,
            style = Typography.headlineMedium,
        )
    }
}
