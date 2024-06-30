package de.hsd.modulearn.logic

class QuizLogic {

    fun isAnswerCorrect(selectedAnswers: List<Int>, correctAnswers: List<Int>?): Boolean {
        if (correctAnswers == null || selectedAnswers.size != correctAnswers.size) {
            return false
        }
        return correctAnswers.containsAll(selectedAnswers)
    }
}