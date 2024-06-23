package de.hsd.modulearn.data.oop1
import kotlinx.serialization.Serializable

@Serializable
data class Quiz(
    val title: String,
    val questions: List<Question>
)
