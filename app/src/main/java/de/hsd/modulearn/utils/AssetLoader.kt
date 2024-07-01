package de.hsd.modulearn.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.module.Chapter
import de.hsd.modulearn.data.module.FinalQuiz
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.data.module.Quiz
import java.io.InputStreamReader

class AssetLoader {

    val fullLectureList: List<Lecture> = getAllLectures()
    val fullQuizList : List <Quiz> = getAllQuizzes()
    val finalQuiz : FinalQuiz = loadFinalQuizFromJson()

    fun getChaptersFromLectureById(id: Int): List<Chapter>? {
        val lecture = fullLectureList.find { it.id == id }
        return lecture?.chapters
    }

    fun getAllQuizzes() : List <Quiz> {
        val quizList: MutableList<Quiz> = mutableListOf()

        for (n in 1..9) {
            val quizFromJson = loadQuizFromJson(n)
            quizList.add(quizFromJson)
        }

        return quizList
    }

    fun getAllLectures() : List <Lecture> {
        val lectureList: MutableList<Lecture> = mutableListOf()

        for (n in 1..9) {
            val lectureFromJson = loadLectureFromJson(n)
            lectureList.add(lectureFromJson)
        }

        return lectureList
    }

    fun getQuizById(id: Int): Quiz? {
        val quiz = fullQuizList.find { it.id == id }
        return quiz
    }

    fun loadQuizFromJson(id : Int): Quiz {
        val fileName = "quizzes/quiz0$id.json"

        val gson = Gson()
        val context = AppContext.getContext()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Quiz>() {}.type
        return gson.fromJson(reader, type)
    }

    fun loadLectureFromJson(id : Int): Lecture {
        val fileName = "lectures/lecture0$id.json"

        val gson = Gson()
        val context = AppContext.getContext()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Lecture>() {}.type
        return gson.fromJson(reader, type)
    }

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



