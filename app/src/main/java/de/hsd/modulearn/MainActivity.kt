package de.hsd.modulearn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.hsd.modulearn.screens.oop1.Oop1Activity
import de.hsd.modulearn.ui.theme.*

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences // Declare SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sharedPreferences = getSharedPreferences("ModulearnPrefs", Context.MODE_PRIVATE) // Initialize SharedPreferences
        setContent {
            ModuLearnTheme {
                ScaffoldExample(this, sharedPreferences) // Pass SharedPreferences to ScaffoldExample
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(activity: ComponentActivity, sharedPreferences: SharedPreferences) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = PrimaryDarkBlue,
                ),
                title = {
                    Text("moduLearn")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = White, // Hintergrundfarbe
                contentColor = Black // Textfarbe
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.List, contentDescription = "List")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp), // Festen Abstand zwischen den Elementen
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryDarkBlue,
                    contentColor = White
                ), modifier = Modifier
                    .width(250.dp)
                    .height(100.dp)
                    .clickable {
                        // Hier wird ein OnClickListener hinzugefügt
                        val intent = Intent(activity, Oop1Activity::class.java)
                        intent.putExtra("points", sharedPreferences.getInt("points", 0)) // Pass current points
                        activity.startActivity(intent)
                    },
            ) {
                Text(
                    text = "OOP1",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Right,
                    style = Typography.headlineSmall
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryDarkBlue,
                    contentColor = White
                ), modifier = Modifier
                    .width(250.dp)
                    .height(100.dp),
            ) {
                Text(
                    text = "MCI",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Right,
                    style = Typography.headlineSmall
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = LightGrey,
                    contentColor = PrimaryDarkBlue
                ), modifier = Modifier
                    .width(250.dp)
                    .height(100.dp),
            ) {
                Text(
                    text = "+",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    style = Typography.titleLarge
                )
            }
        }
    }
}
