package de.hsd.modulearn.data.oop1
import kotlinx.serialization.Serializable

@Serializable
data class Oop1Lektion(
    val title: String,
    val description: String,
   // val quizzes: List<Quiz>,
)
