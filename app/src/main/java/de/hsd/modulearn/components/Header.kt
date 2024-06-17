package de.hsd.modulearn.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.hsd.modulearn.ui.theme.DarkMidGrey
import de.hsd.modulearn.ui.theme.PrimaryDarkBlue
import de.hsd.modulearn.ui.theme.Typography

@Composable
fun Header(title: String, backButton: Boolean = true) {
    //val outerNavController: NavController<Screen?> by localDI().instance(tag = "outer")
    //val navController: NavController<Screen> by localDI().instance()

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (backButton) Button(
            colors = ButtonDefaults.buttonColors(PrimaryDarkBlue),
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .align(Alignment.TopStart)
                .shadow(
                    elevation = 40.dp,
                    spotColor = DarkMidGrey
                )
                .size(32.dp)
                .fillMaxHeight(),
            contentPadding = PaddingValues(5.dp),
            onClick = {
                //navController.pop()
            },
        ) {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
        }
        Text(
            text = title,
            style = Typography.headlineSmall,
            color = PrimaryDarkBlue,
            modifier = Modifier
                .align(Alignment.TopCenter),
            textAlign = TextAlign.Center,
        )
    }
}
