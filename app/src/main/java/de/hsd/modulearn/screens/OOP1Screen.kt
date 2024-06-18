package de.hsd.modulearn.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import de.hsd.modulearn.ui.theme.*

import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header

@Composable
fun OOP1Screen(navController: NavController) {
    Scaffold(
        topBar = { Header(title = "OOP1") },
        bottomBar = { Footer(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lektionenübersicht", style = Typography.titleLarge)
                Spacer(modifier = Modifier.weight(1f))
                ElevatedButton(
                    onClick = { // do something
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        contentColor = Black
                    )
                ) {
                    Text("Roadmap")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            DisplayOpp1Chapters()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayOpp1Chapters() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(4) { index ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryDarkBlue,
                    contentColor = White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
            ) {
                Text(
                    text = "Lektion ${index + 1}",
                    modifier = Modifier.padding(20.dp),
                )
            }
        }
    }
}
