package de.hsd.modulearn

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
    // Dies habe ich neu gemacht: Initialisierung von SharedPreferences für die Punkteverwaltung
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Dies habe ich neu gemacht: Initialisierung von SharedPreferences für die Punkteverwaltung
        sharedPreferences = getSharedPreferences("de.hsd.modulearn.PREFERENCES", Context.MODE_PRIVATE)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = homescreen, builder = {
                composable(homescreen) {
                    HomeScreen(navController)
                }
                composable(progressscreen) {
                    ProgressScreen(navController)
                }
                composable(oop1home) {
                    Oop1Home(navController)
                }
                composable(oop1lektion + "/{title}") {
                    val title = it.arguments?.getString("title")
                    Oop1LektionView(navController, title ?: "Kein Titel")
                }
                composable(oop1kapitel + "/{title}") {
                    val title = it.arguments?.getString("title")
                    Oop1ChapterView(navController, title ?: "Kein Titel", content = "Hallo Test")
                }
            })
        }
    }

    // Dies habe ich neu gemacht: Methode zum Abrufen der aktuellen Punkte aus SharedPreferences
    fun getPoints(): Int {
        return sharedPreferences.getInt("points", 0)
    }

    // Dies habe ich neu gemacht: Methode zum Speichern der Punkte in SharedPreferences
    fun setPoints(points: Int) {
        with(sharedPreferences.edit()) {
            putInt("points", points)
            apply()
        }
    }
}
