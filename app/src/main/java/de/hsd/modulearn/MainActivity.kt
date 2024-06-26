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
import de.hsd.modulearn.data.Routes.chatBot
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.data.Routes.oop1lektion
import de.hsd.modulearn.data.Routes.oop1kapitel
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.data.Routes.oop1quiz
import de.hsd.modulearn.data.Routes.quizzesscreen
import de.hsd.modulearn.screens.*
import de.hsd.modulearn.screens.oop1screens.*
import de.hsd.modulearn.utils.JsonReader


class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val jsonReader = JsonReader()
        //jsonReader.printAllQuestions(this)

        sharedPreferences = getSharedPreferences("de.hsd.modulearn.PREFERENCES", Context.MODE_PRIVATE)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = homescreen, builder = {
                composable(homescreen){
                    HomeScreen(navController)
                }
                composable(progressscreen) {
                    ProgressScreen(navController)
                }
                composable(quizzesscreen) {
                    QuizzesScreen(navController)
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
                composable(oop1quiz+"/{title}"){
                    val title = it.arguments?.getString("title")
                    Oop1Quiz(navController, title?:"Kein Titel")
                }

                composable(chatBot){
                    ChatBotView(navController)
                }
            } )
        }
    }

    // Für die Punkte: Methode zum Abrufen der aktuellen Punkte aus SharedPreferences
    fun getPoints(): Int {
        return sharedPreferences.getInt("points", 0)
    }

    // Für die Punkte: Methode zum Speichern der Punkte in SharedPreferences
    fun setPoints(points: Int) {
        with(sharedPreferences.edit()) {
            putInt("points", points)
            apply()
        }
    }

}
