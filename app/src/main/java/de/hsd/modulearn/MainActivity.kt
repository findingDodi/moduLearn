package de.hsd.modulearn

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.hsd.modulearn.data.Routes.chatbotview
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.moduleview
import de.hsd.modulearn.data.Routes.lectureview
import de.hsd.modulearn.data.Routes.chapterview
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.data.Routes.quizview
import de.hsd.modulearn.data.Routes.quizresultview
import de.hsd.modulearn.data.Routes.roadmapview
import de.hsd.modulearn.data.Routes.quizzesscreen
import de.hsd.modulearn.screens.*
import de.hsd.modulearn.screens.module.*
import de.hsd.modulearn.utils.AppContext


class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppContext.initialize(this)
        enableEdgeToEdge()

        sharedPreferences = getSharedPreferences("de.hsd.modulearn.PREFERENCES", Context.MODE_PRIVATE)

        setContent {
            val navController = rememberNavController()
            val points = remember { mutableStateOf(getPoints()) }

            NavHost(navController = navController, startDestination = homescreen, builder = {
                composable(homescreen) {
                    HomeScreen(navController)
                }

                composable(progressscreen) {
                    ProgressScreen(navController, points.value)
                }

                composable(quizzesscreen) {
                    QuizzesScreen(navController)
                }

                composable(moduleview) {
                    Oop1Home(navController)
                }

                composable("$lectureview/{id}/{title}") {
                    val id = it.arguments?.getString("id")?.toIntOrNull()
                    val title = it.arguments?.getString("title")
                    Oop1LektionView(navController, id?:1,title?:"Kein Titel")
                }

                composable("$chapterview/{title}/{content}") {
                    val title = it.arguments?.getString("title")
                    val content = it.arguments?.getString("content")
                    Oop1ChapterView(navController, title?:"Kein Titel", content?:"kein Inhalt")
                }

                composable("$quizview/{id}") {
                    val id = it.arguments?.getString("id")?.toIntOrNull()
                    Oop1Quiz(navController, id?:1)
                }

                composable(chatbotview) {
                    ChatBotView(navController)
                }

                composable(roadmapview){
                    Oop1RoadmapView(navController)
                }

                composable("$quizresultview/{correctAnswers}/{quizQuestionsSize}") {
                    val correctAnswers = it.arguments?.getString("correctAnswers")?.toIntOrNull()
                    val quizQuestionsSize = it.arguments?.getString("quizQuestionsSize")?.toIntOrNull()
                    QuizResultScreen(navController, correctAnswers?:1,quizQuestionsSize?:1 )
                }
            })
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
