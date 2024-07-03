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
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes.chatbotview
import de.hsd.modulearn.data.Routes.homescreen
import de.hsd.modulearn.data.Routes.moduleview
import de.hsd.modulearn.data.Routes.lectureview
import de.hsd.modulearn.data.Routes.chapterview
import de.hsd.modulearn.data.Routes.finalQuizViewIntro
import de.hsd.modulearn.data.Routes.finalQuizViewStart
import de.hsd.modulearn.data.Routes.progressscreen
import de.hsd.modulearn.data.Routes.quizview
import de.hsd.modulearn.data.Routes.quizresultview
import de.hsd.modulearn.data.Routes.roadmapview
import de.hsd.modulearn.data.Routes.quizzesscreen
import de.hsd.modulearn.screens.*
import de.hsd.modulearn.screens.module.*
import de.hsd.modulearn.utils.AppContext
import java.time.LocalDate

/**
 * Hauptaktivität der Anwendung. Verwaltet die Navigation und andere Hauptfunktionalitäten.
 */
class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    /**
     * Initialisierungsmethode der Aktivität.
     */
    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppContext.initialize(this)
        enableEdgeToEdge()

        sharedPreferences = getSharedPreferences("de.hsd.modulearn.PREFERENCES", Context.MODE_PRIVATE)

        setContent {
            val navController = rememberNavController()
            val points = remember { mutableStateOf(getPoints()) }
            val showThirdBadge = remember { mutableStateOf(getShowThirdBadge()) }
            val unlockeModules = remember { mutableStateOf(getUnlockedModules()) }
            val moduleProgress = remember { mutableStateOf(getProgress()) }

            NavHost(navController = navController, startDestination = homescreen, builder = {
                composable(homescreen) {
                    HomeScreen(navController)
                }

                composable(progressscreen) {
                    ProgressScreen(navController, points.value, showThirdBadge.value, moduleProgress.value)
                }

                composable(quizzesscreen) {
                    QuizzesScreen(navController)
                }

                composable(moduleview) {
                    ModuleView(navController)
                }

                composable("$lectureview/{id}/{title}") {
                    val id = it.arguments?.getString("id")?.toIntOrNull()
                    val title = it.arguments?.getString("title")
                    LectureView(navController, id ?: 1, title ?: "")
                }

                composable("$chapterview/{title}/{content}") {
                    val title = it.arguments?.getString("title")
                    val content = it.arguments?.getString("content")
                    ChapterView(navController, title ?: "Kein Titel", content ?: "kein Inhalt")
                }

                composable("$quizview/{id}") {
                    val id = it.arguments?.getString("id")?.toIntOrNull()
                    Oop1Quiz(navController, id ?: 1)
                }

                composable(chatbotview) {
                    ChatBotView(navController)
                }

                composable(roadmapview) {
                    RoadmapView(navController)
                }

                composable(finalQuizViewStart) {
                    FinalQuizViewStart(navController, showThirdBadge.value) { newValue ->
                        showThirdBadge.value = newValue
                        setShowThirdBadge(newValue)
                    }
                }

                composable(finalQuizViewIntro) {
                    FinalQuizViewIntro(navController)
                }

                composable("$quizresultview/{correctAnswers}/{quizQuestionsSize}/{id}") {
                    val correctAnswers = it.arguments?.getString("correctAnswers")?.toIntOrNull()
                    val quizQuestionsSize = it.arguments?.getString("quizQuestionsSize")?.toIntOrNull()
                    val id = it.arguments?.getString("id")?.toIntOrNull()
                    QuizResultView(navController, correctAnswers ?: 1, quizQuestionsSize ?: 1, id ?: 1)
                }
            })
        }

        // Streak aktualisieren bei App-Start
        updateStreak()
    }

    /**
     * Methode zum Abrufen der aktuellen Punkte aus den SharedPreferences.
     *
     * @return Die aktuellen Punkte
     */
    fun getPoints(): Int {
        return sharedPreferences.getInt("points", 0)
    }

    /**
     * Methode zum Speichern der Punkte in den SharedPreferences.
     *
     * @param points Die zu speichernden Punkte
     */
    fun setPoints(points: Int) {
        with(sharedPreferences.edit()) {
            putInt("points", points)
            apply()
        }
    }

    /**
     * Aktualisiert die Streak basierend auf dem letzten Öffnungsdatum der App.
     */
    private fun updateStreak() {
        val lastOpenDate = getLastOpenDate()
        val today = LocalDate.now().toEpochDay()

        // Überprüfe, ob die App gestern geöffnet wurde (um Mitternacht)
        if (lastOpenDate == today - 1) {
            // Wenn ja, erhöhe die Streak um 1
            val currentStreak = getStreak()
            setStreak(currentStreak + 1)
        } else if (lastOpenDate < today - 1) {
            // Wenn die App gestern nicht geöffnet wurde, setze die Streak zurück
            setStreak(1)
        }

        // Speichere das heutige Datum als das zuletzt geöffnete Datum
        setLastOpenDate(today)
    }

    /**
     * Methode zum Abrufen der aktuellen Streak aus den SharedPreferences.
     *
     * @return Die aktuelle Streak
     */
    fun getStreak(): Int {
        return sharedPreferences.getInt("streak", 0)
    }

    /**
     * Methode zum Speichern der Streak in den SharedPreferences.
     *
     * @param streak Die zu speichernde Streak
     */
    fun setStreak(streak: Int) {
        with(sharedPreferences.edit()) {
            putInt("streak", streak)
            apply()
        }
    }

    /**
     * Methode zum Abrufen des zuletzt geöffneten Datums aus den SharedPreferences.
     *
     * @return Das zuletzt geöffnete Datum
     */
    fun getLastOpenDate(): Long {
        return sharedPreferences.getLong("last_open_date", 0L)
    }

    /**
     * Methode zum Speichern des zuletzt geöffneten Datums in den SharedPreferences.
     *
     * @param date Das zu speichernde Datum
     */
    fun setLastOpenDate(date: Long) {
        with(sharedPreferences.edit()) {
            putLong("last_open_date", date)
            apply()
        }
    }


    /**
     * Methode zum Abrufen des Status des dritten Badges aus den SharedPreferences.
     *
     * @return true, wenn das dritte Badge angezeigt werden soll, false sonst
     */
    fun getShowThirdBadge(): Boolean {
        return sharedPreferences.getBoolean("show_third_badge", false)
    }

    /**
     * Methode zum Speichern des Status des dritten Badges in den SharedPreferences.
     *
     * @param show true, wenn das dritte Badge angezeigt werden soll, false sonst
     */
    fun setShowThirdBadge(show: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean("show_third_badge", show)
            apply()
        }
    }


    /**
     * Methode zum Abrufen des aktuellen Fortschritts der freigeschalteten Module aus den SharedPreferences.
     *
     * @return Der aktuelle Fortschritt der freigeschalteten Module
     */
    fun getUnlockedModules(): Int {
        return sharedPreferences.getInt("unlocked_modules", 1)
    }

    /**
     * Methode zum Freischalten des nächsten Moduls und Speichern der Änderung in den SharedPreferences.
     */
    fun setunlockNextModule() {
        val currentUnlocked = getUnlockedModules()
        with(sharedPreferences.edit()) {
            putInt("unlocked_modules", currentUnlocked + 1)
            apply()
        }
    }

    /**
     * Methode zum Abrufen des aktuellen Modulfortschritts aus den SharedPreferences.
     *
     * @return Der aktuelle Modulfortschritt
     */
    fun getProgress(): Int {
        return sharedPreferences.getInt("progress", 10)
    }

    /**
     * Methode zum Erhöhen des Modulfortschritts um 10 und Speichern der Änderung in den SharedPreferences.
     */
    fun setProgress() {
        val moduleProgress = getProgress()
        with(sharedPreferences.edit()) {
            putInt("progress", moduleProgress + 10)
            apply()
        }
    }

}
