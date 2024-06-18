package de.hsd.modulearn

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
