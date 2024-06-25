package de.hsd.modulearn.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.hsd.modulearn.data.oop1.Lektion
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

    fun loadLectureFromJson(context: Context): Lektion {
        val gson = Gson()
        val jsonFile = context.assets.open("lectures/lecture01.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Lektion>() {}.type
        return gson.fromJson(reader, type)
    }

    fun loadAllLecturesFromJson(context: Context): List<Lektion> {
        val gson = Gson()
        val jsonFile = context.assets.open("lectures/lecture01.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<List<Lektion>>() {}.type
        return gson.fromJson(reader, type)
    }

}
