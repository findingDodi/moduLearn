package de.hsd.modulearn.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.hsd.modulearn.data.oop1.Lecture
import de.hsd.modulearn.data.oop1.Quiz
import java.io.InputStreamReader


class JsonReader {

    fun loadQuizFromJson(context: Context): Quiz {
        val gson = Gson()
        val jsonFile = context.assets.open("quizzes/quiz01.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Quiz>() {}.type
        return gson.fromJson(reader, type)
    }

    fun loadAllQuizzesFromJson(context: Context): List<Quiz> {
        val gson = Gson()
        val jsonFile = context.assets.open("lectures/lecture01.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<List<Quiz>>() {}.type
        return gson.fromJson(reader, type)
    }

    fun loadLectureFromJson(context: Context): Lecture {
        val gson = Gson()
        val jsonFile = context.assets.open("lectures/lecture01.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Lecture>() {}.type
        return gson.fromJson(reader, type)
    }

    fun loadAllLecturesFromJson(context: Context): List<Lecture> {
        val gson = Gson()
        val jsonFile = context.assets.open("lectures/lecture01.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<List<Lecture>>() {}.type
        return gson.fromJson(reader, type)
    }

}
