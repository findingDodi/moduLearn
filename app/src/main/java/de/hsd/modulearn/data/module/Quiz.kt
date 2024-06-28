package de.hsd.modulearn.data.module

data class Quiz(
    val id : Int,
    val title: String,
    val questions: List<Question>
)
