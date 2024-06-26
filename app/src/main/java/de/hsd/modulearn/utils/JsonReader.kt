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


    fun loadQuizFromJson(id : Int): Quiz {
        val fileName = "quizzes/quiz0$id.json"

        val gson = Gson()
        val context = AppContext.getContext()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Quiz>() {}.type
        return gson.fromJson(reader, type)
    }

    fun loadAllLecturesFromJson(): List<Lecture> {
        val gson = Gson()
        val lectureList = mutableListOf<Lecture>()
        val context = AppContext.getContext()

        // Hole eine Liste aller Dateien im "lectures" Ordner
        val assetManager = context.assets
        val files = assetManager.list("lectures")

        // Überprüfe, ob Dateien gefunden wurden
        if (files != null) {
            for (fileName in files) {
                if (fileName.endsWith(".json")) {
                    val jsonFile = assetManager.open("lectures/$fileName")
                    val reader = InputStreamReader(jsonFile)
                    val type = object : TypeToken<List<Lecture>>() {}.type
                    val lectures: List<Lecture> = gson.fromJson(reader, type)
                    lectureList.addAll(lectures)
                }
            }
        }

        return lectureList
    }

    fun loadBundledLectureFromJson(): List <Lecture>  {
        val fileName = "lectures/lectures_combined.json"

        val context = AppContext.getContext()

        val gson = Gson()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken <List<Lecture>>() {}.type
        return gson.fromJson(reader, type)
    }


}
