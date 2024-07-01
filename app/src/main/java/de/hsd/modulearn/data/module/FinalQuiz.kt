package de.hsd.modulearn.data.module

data class FinalQuiz(
    val moduleTitle : String,
    val questions : List <FinalQuizQuestion>,
)
