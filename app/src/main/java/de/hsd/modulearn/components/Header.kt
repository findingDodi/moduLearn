package de.hsd.modulearn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.MainActivity
import de.hsd.modulearn.R
import de.hsd.modulearn.theme.*

@Composable
fun Header(
    title: String,
    backButton: Boolean = true,
    navController: NavController
) {
    // Dies habe ich neu gemacht: Kontext und MainActivity-Instanz abrufen
    val context = LocalContext.current
    val mainActivity = context as MainActivity
    val points = mainActivity.getPoints() // Dies habe ich neu gemacht: Abrufen der aktuellen Punkte

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(10.dp)
            .background(White)
            .fillMaxWidth()
            .padding(PaddingValues(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 15.dp))
    ) {
        if (backButton) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_back_24),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            // zurück navigieren
                            navController.popBackStack()
                        }
                )
            }
        }
        Column {
            Text(
                text = "$title",
                style = Typography.headlineLarge,
                color = PrimaryDarkBlue
            )
        }

        // Dies habe ich neu gemacht: Anzeige der aktuellen Punkte im Header
        Text(
            text = "$points P",
            style = Typography.headlineSmall,
            color = White,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(PrimaryDarkLilac)
                .padding(PaddingValues(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp))
        )
    }
}
