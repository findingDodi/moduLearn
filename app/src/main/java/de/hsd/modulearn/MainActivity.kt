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

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.data.Routes.oop1lektion
import de.hsd.modulearn.data.Routes.oop1kapitel
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.screens.*
import de.hsd.modulearn.screens.oop1screens.*

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences // Dies habe ich neu gemacht: Declare SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sharedPreferences = getSharedPreferences("ModulearnPrefs", Context.MODE_PRIVATE) // Dies habe ich neu gemacht: Initialize SharedPreferences
        setContent {
            ModuLearnTheme {
                ScaffoldExample(this, sharedPreferences) // Dies habe ich neu gemacht: Pass SharedPreferences to ScaffoldExample
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
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = homescreen, builder = {
                composable(homescreen){
                    HomeScreen(navController)
                }
                composable(progressscreen) {
                    ProgressScreen(navController)
                }
                composable(oop1home){
                    Oop1Home(navController)
                }
                composable(oop1lektion+"/{title}"){
                    val title = it.arguments?.getString("title")
                    Oop1LektionView(navController, title?:"Kein Titel")
                }
                composable(oop1kapitel+"/{title}"){
                    val title = it.arguments?.getString("title")
                    Oop1ChapterView(navController, title?:"Kein Titel", content = "Hallo Test")
                }
            } )
        }
    }
}
