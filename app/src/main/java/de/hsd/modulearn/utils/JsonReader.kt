package de.hsd.modulearn.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

data class MyData(
    val id: Int,
    val name: String
)


class JsonReader {

    fun loadJson(context: Context): List<MyData> {
        val gson = Gson()
        val jsonFile = context.assets.open("data.json")
        val reader = InputStreamReader(jsonFile)
        val type = object : TypeToken<List<MyData>>() {}.type
        return gson.fromJson(reader, type)
    }
}
