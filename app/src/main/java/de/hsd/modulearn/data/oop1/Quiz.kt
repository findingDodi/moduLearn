package de.hsd.modulearn.data.oop1
import kotlinx.serialization.Serializable

data class Quiz(
    val id : Int,
    val title: String,
    val questions: List<Question>
)
