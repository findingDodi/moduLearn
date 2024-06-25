package de.hsd.modulearn.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.hsd.modulearn.data.oop1.Question
import java.io.InputStreamReader

data class Quiz(
    val title: String,
    val questions: List<Question>
)


class JsonReader {

    fun loadJson(context: Context): List<Quiz> {
        val gson = Gson()
        val jsonFile = context.assets.open("quizzes/quiz01.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<List<Quiz>>() {}.type
        return gson.fromJson(reader, type)
    }
}
