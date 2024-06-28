package de.hsd.modulearn.utils

class QuizLogic {

    fun isAnswerCorrect(selectedAnswer: Int, correctAnswers: List<Int>?): Boolean {
        return correctAnswers?.contains(selectedAnswer) ?: false
    }

    fun areMultipleChoiceAnswersCorrect(selectedAnswers: List<Int>, correctAnswers: List<Int>?): Boolean {
        if (correctAnswers == null || selectedAnswers.size != correctAnswers.size) {
            return false
        }
        return correctAnswers.containsAll(selectedAnswers)
    }
}