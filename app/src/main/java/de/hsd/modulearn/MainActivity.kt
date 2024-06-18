package de.hsd.modulearn


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.hsd.modulearn.screens.HomeScreen
import de.hsd.modulearn.screens.OOP1Screen
import de.hsd.modulearn.screens.ProgressScreen
import de.hsd.modulearn.screens.*
import de.hsd.modulearn.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "HomeScreen", builder = {
                composable("HomeScreen") {
                    HomeScreen(navController)
                }
                composable("OOP1LectureScreen") {
                    OOP1Screen(navController)
                }
                composable("ProgressScreen") {
                    ProgressScreen(navController)
                }
            })
        }
    }
}
