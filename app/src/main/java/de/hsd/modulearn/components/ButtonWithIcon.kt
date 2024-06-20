package de.hsd.modulearn.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.theme.PrimaryDarkBlue
import de.hsd.modulearn.theme.Typography
import de.hsd.modulearn.theme.White

@Composable
fun ButtonWithIcon(
    @DrawableRes iconId: Int,
    backgroundcolor: Color,
    color: Color,
    text : String,
    destinationRoute : String,
    navController:NavController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundcolor)
            .padding(10.dp)
            .clickable {
                // zurück navigieren
                navController.navigate(destinationRoute)
            }
        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "Back",
                    tint = color,
                    modifier = Modifier
                        .size(24.dp)
                )

                Text(
                    text = text,
                    color = color,
                    style = Typography.labelLarge,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }
    }

