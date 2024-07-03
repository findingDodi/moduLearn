package de.hsd.modulearn.data.module

/**
 * Datenklasse, die ein Abschlussquiz darstellt, das einem Modul zugeordnet ist.
 *
 * @property moduleTitle Der Titel des Moduls, zu dem das Abschlussquiz gehört.
 * @property questions Die Liste der [FinalQuizQuestion]-Objekte, die die Quizfragen repräsentieren.
 */
data class FinalQuiz(
    val moduleTitle : String,
    val questions : List <FinalQuizQuestion>,
)
