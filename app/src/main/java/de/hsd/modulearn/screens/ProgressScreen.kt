package de.hsd.modulearn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.ButtonChatBot
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.theme.*
import de.hsd.modulearn.utils.JsonReader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("Fortschritt", false, navController) },
        bottomBar = { Footer(navController, 3) }

    ) {innerPadding ->
        val context = LocalContext.current
        val jsonData = JsonReader().loadJson(context)

        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                jsonData.forEach { item ->
                    Text(
                        text = "${item.id}: ${item.name}",
                        style = Typography.headlineSmall,
                        modifier = Modifier
                            .padding(PaddingValues(bottom= 15.dp))
                    )
                }

                ButtonChatBot(navController)
            }

        }

    }
}