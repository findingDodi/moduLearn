package de.hsd.modulearn.screens.oop1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import de.hsd.modulearn.ui.theme.*

import androidx.compose.ui.Alignment

class Oop1Activity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences // Dies habe ich neu gemacht: Declare SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("ModulearnPrefs", Context.MODE_PRIVATE) // Dies habe ich neu gemacht: Initialize SharedPreferences
        setContent {
            ModuLearnTheme {
                Oop1Screen(this, sharedPreferences) // Dies habe ich neu gemacht: Pass SharedPreferences to Oop1Screen
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Oop1Screen(activity: ComponentActivity, sharedPreferences: SharedPreferences) {
    var points by remember { mutableStateOf(sharedPreferences.getInt("points", 0)) } // Dies habe ich neu gemacht: Read points from SharedPreferences
    val clickedLessons = remember {
        mutableStateListOf(
            sharedPreferences.getBoolean("lesson1_clicked", false),
            sharedPreferences.getBoolean("lesson2_clicked", false),
            sharedPreferences.getBoolean("lesson3_clicked", false)
        )
    } // Dies habe ich neu gemacht: List to store clicked states

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
                        // Show points
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryDarkLilac,
                        contentColor = White
                    )
                ) {
                    Text("$points P", style = Typography.headlineSmall) // Dies habe ich neu gemacht: Display current points
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
            displayOpp1Chapters(
                activity,
                sharedPreferences,
                clickedLessons,
                onPointsUpdated = { newPoints ->
                    points = newPoints // Dies habe ich neu gemacht: Update points state
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun displayOpp1Chapters(
    activity: ComponentActivity,
    sharedPreferences: SharedPreferences,
    clickedLessons: MutableList<Boolean>,
    onPointsUpdated: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(3) { index ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (clickedLessons[index]) LightGrey else PrimaryDarkBlue,
                    contentColor = if (clickedLessons[index]) Black else White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = !clickedLessons[index]
                    ) {
                        val increment = when (index) {
                            0 -> 20  // Lektion 1
                            1 -> 55  // Lektion 2
                            2 -> 1000 // Lektion 3
                            else -> 0
                        }
                        val newPoints = sharedPreferences.getInt("points", 0) + increment // Dies habe ich neu gemacht: Increase points based on lesson
                        sharedPreferences.edit()
                            .putInt("points", newPoints)
                            .putBoolean("lesson${index + 1}_clicked", true) // Dies habe ich neu gemacht: Mark lesson as clicked
                            .apply() // Dies habe ich neu gemacht: Save new points and clicked state to SharedPreferences
                        clickedLessons[index] = true // Dies habe ich neu gemacht: Update local clicked state
                        onPointsUpdated(newPoints) // Dies habe ich neu gemacht: Update points in the UI
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
