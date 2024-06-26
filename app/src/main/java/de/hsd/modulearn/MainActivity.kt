package de.hsd.modulearn

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
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
import de.hsd.modulearn.data.oop1.Lecture
import de.hsd.modulearn.screens.*
import de.hsd.modulearn.screens.oop1screens.*
import de.hsd.modulearn.utils.AppContext
import de.hsd.modulearn.utils.AssetLoader
import de.hsd.modulearn.utils.JsonReader


class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContext.initialize(this)

        // print(AssetLoader().fullLectureList)
        enableEdgeToEdge()

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
                composable(oop1lektion+"/{id}/{title}"){
                    val id = it.arguments?.getString("id")?.toIntOrNull()
                    val title = it.arguments?.getString("title")
                    Oop1LektionView(navController, id?:1,title?:"Kein Titel")
                }
                composable(oop1kapitel+"/{title}/{content}"){
                    val title = it.arguments?.getString("title")
                    val content = it.arguments?.getString("content")
                    Oop1ChapterView(navController, title?:"Kein Titel", content?:"kein Inhalt")
                }
                composable(oop1quiz+"/{id}"){
                    val id = it.arguments?.getString("id")?.toIntOrNull()
                    Oop1Quiz(navController, id?:1)
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
