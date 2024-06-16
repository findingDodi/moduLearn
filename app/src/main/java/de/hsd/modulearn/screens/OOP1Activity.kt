package de.hsd.modulearn.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import de.hsd.modulearn.ui.theme.*

import androidx.compose.ui.Alignment

class Oop1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModuLearnTheme {
                Oop1Screen(this)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Oop1Screen(activity: ComponentActivity) {
    Column {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = White,
                titleContentColor = PrimaryDarkBlue,
            ),
            title = {
                Text("OOP1")
            },
            navigationIcon = {
                IconButton(onClick = {
                    val intent = Intent(activity, de.hsd.modulearn.MainActivity::class.java)
                    activity.startActivity(intent)
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                Button(
                    onClick = {
                        // do something
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryDarkLilac,
                        contentColor = White
                    )
                ) {
                    Text("50P", style = Typography.headlineSmall)
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
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
            displayOpp1Chapters()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun displayOpp1Chapters() {
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
