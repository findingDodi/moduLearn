package de.hsd.modulearn.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.hsd.modulearn.data.module.Lecture
import de.hsd.modulearn.data.module.Quiz
import java.io.InputStreamReader


class JsonReader {

    fun loadQuizFromJson(id : Int): Quiz {
        val fileName = "quizzes/quiz0$id.json"

        val gson = Gson()
        val context = AppContext.getContext()
        val jsonFile = context.assets.open(fileName)
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<Quiz>() {}.type
        return gson.fromJson(reader, type)
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
