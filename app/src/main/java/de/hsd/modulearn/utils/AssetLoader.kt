package de.hsd.modulearn.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.hsd.modulearn.data.module.Chapter
import de.hsd.modulearn.data.module.FinalQuiz
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.data.module.Quiz
import java.io.InputStreamReader

/**
 * Eine Utility-Klasse zum Laden von Daten aus JSON-Dateien im Assets-Ordner.
 */
class AssetLoader {

    // Lädt die komplette Liste aller Vorlesungen
    val fullLectureList: List<Lecture> = getAllLectures()

    // Lädt die komplette Liste aller Quizfragen
    val fullQuizList: List<Quiz> = getAllQuizzes()

    // Lädt das abschließende Quiz
    val finalQuiz: FinalQuiz = loadFinalQuizFromJson()

    /**
     * Gibt die Kapitel einer Lektion anhand ihrer ID zurück.
     *
     * @param id Die ID der Lektion
     * @return Die Liste der Kapitel oder null, falls die Lektion nicht gefunden wurde.
     */
    fun getChaptersFromLectureById(id: Int): List<Chapter>? {
        val lecture = fullLectureList.find { it.id == id }
        return lecture?.chapters
    }

    /**
     * Gibt den Titel einer Lektion anhand ihrer ID zurück.
     *
     * @param id Die ID der Lektion
     * @return Der Titel der Lektion oder null, falls die Lektion nicht gefunden wurde.
     */
    fun getTitleFromLectureById(id: Int): String? {
        val lecture = fullLectureList.find { it.id == id }
        return lecture?.title
    }

    /**
     * Lädt alle Quizfragen.
     *
     * @return Die Liste aller Quizfragen.
     */
    fun getAllQuizzes() : List <Quiz> {
        val quizList: MutableList<Quiz> = mutableListOf()

        for (n in 1..9) {
            val quizFromJson = loadQuizFromJson(n)
            quizList.add(quizFromJson)
        }

        return quizList
    }

    /**
     * Lädt alle Lektionen.
     *
     * @return Die Liste aller Lektionen.
     */
    fun getAllLectures() : List <Lecture> {
        val lectureList: MutableList<Lecture> = mutableListOf()

        for (n in 1..9) {
            val lectureFromJson = loadLectureFromJson(n)
            lectureList.add(lectureFromJson)
        }

        return lectureList
    }

    /**
     * Lädt ein Quiz aus einer JSON-Datei anhand seiner ID
     *
     * @param id Die ID des Quizzes
     * @return Das geladene Quiz
     */
    fun getQuizById(id: Int): Quiz? {
        val quiz = fullQuizList.find { it.id == id }
        return quiz
    }

    /**
     * Lädt ein Quiz aus einer JSON-Datei
     *
     * @param id Die ID des Quizzes
     * @return Das geladene Quiz
     */
    fun loadQuizFromJson(id : Int): Quiz {
        val fileName = "quizzes/quiz0$id.json"

        val gson = Gson()
        val context = AppContext.getContext()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Quiz>() {}.type
        return gson.fromJson(reader, type)
    }

    /**
     * Lädt eine Lektion aus einer JSON-Datei
     *
     * @param id Die ID der Lektion
     * @return Die geladene Lektion
     */
    fun loadLectureFromJson(id : Int): Lecture {
        val fileName = "lectures/lecture0$id.json"

        val gson = Gson()
        val context = AppContext.getContext()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Lecture>() {}.type
        return gson.fromJson(reader, type)
    }

    /**
     * Lädt das abschließende Quiz aus einer JSON-Datei.
     *
     * @return Das geladene abschließende Quiz
     */
    fun loadFinalQuizFromJson(): FinalQuiz {
        val fileName = "quizzes/finalQuiz.json"

        val context = AppContext.getContext()

        val gson = Gson()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<FinalQuiz>(){}.type
        return gson.fromJson(reader, type)
    }


}



