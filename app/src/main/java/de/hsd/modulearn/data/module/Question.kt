package de.hsd.modulearn.data.module

data class Question(
    val questionText: String?,
    val answerOptions: List<String>?,
    val type: String?,
    val answer: List<Int>?,
    val explanation: String?,
)
