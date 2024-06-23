package de.hsd.modulearn.data.oop1
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val text: String,
    val options: List<String>,
    val type: String,
    val answer: List<Int>,
    val explanation: String
)
