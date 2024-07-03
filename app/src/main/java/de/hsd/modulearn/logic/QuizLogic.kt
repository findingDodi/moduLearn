package de.hsd.modulearn.logic

/**
 * Eine Hilfsklasse für quizbezogene Logik.
 */
class QuizLogic {

    /**
     * Überprüft, ob die ausgewählten Antworten basierend auf den angegebenen korrekten Antworten richtig sind.
     *
     * @param selectedAnswers Die Liste der Indizes, die die ausgewählten Antworten repräsentieren.
     * @param correctAnswers Die Liste der Indizes, die die korrekten Antworten repräsentieren.
     * @return `true`, wenn alle ausgewählten Antworten korrekt sind; `false` sonst.
     */
    fun isAnswerCorrect(selectedAnswers: List<Int>, correctAnswers: List<Int>?): Boolean {
        // Wenn correctAnswers null ist oder die Größen von selectedAnswers und correctAnswers unterschiedlich sind, wird false zurückgegeben
        if (correctAnswers == null || selectedAnswers.size != correctAnswers.size) {
            return false
        }
        // Überprüft, ob alle Elemente in correctAnswers in selectedAnswers enthalten sind
        return correctAnswers.containsAll(selectedAnswers)
    }
}