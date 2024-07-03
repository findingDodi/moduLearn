package de.hsd.modulearn.data.module

/**
 * Datenklasse, die ein Quiz innerhalb eines Moduls repräsentiert.
 *
 * @property id Die eindeutige Kennung des Quiz.
 * @property title Der Titel des Quiz.
 * @property questions Die Liste der [Question]-Objekte, die die Fragen im Quiz repräsentieren.
 */

data class Quiz(
    val id : Int,
    val title: String,
    val questions: List<Question>
)
