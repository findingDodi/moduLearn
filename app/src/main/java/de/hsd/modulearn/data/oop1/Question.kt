package de.hsd.modulearn.data.oop1
import kotlinx.serialization.Serializable

data class Question(
    val questionText: String,
    val answerOptions: List<String>,
    val answer: List<Int>,
    val explanation: String
)
