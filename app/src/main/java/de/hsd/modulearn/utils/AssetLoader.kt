package de.hsd.modulearn.utils

import android.content.Context
import de.hsd.modulearn.data.oop1.Chapter
import de.hsd.modulearn.data.oop1.Lecture
import de.hsd.modulearn.data.oop1.Quiz

class AssetLoader {
    val fullLectureList: List<Lecture> = JsonReader().loadBundledLectureFromJson()

    val fullQuizList : List <Quiz> = getAllQuizzes()

    fun getChaptersFromLectureById(id: Int): List<Chapter>? {
        val lecture = fullLectureList.find { it.id == id }
        return lecture?.chapters
    }

    fun getAllQuizzes() : List <Quiz> {
        val quizList: MutableList<Quiz> = mutableListOf()

        for (n in 1..9) {
            val quizFromJson = JsonReader().loadQuizFromJson(n)
            quizList.add(quizFromJson)
        }

        return quizList
    }

    fun getQuizById(id: Int): Quiz? {
        val quiz = fullQuizList.find { it.id == id }
        return quiz
    }
}



