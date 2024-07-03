package de.hsd.modulearn.data.module

/**
 * Datenklasse, die ein Frageobjekt repräsentiert.
 *
 * @property questionText Der Text der Frage.
 * @property answerOptions Die Liste der Antwortoptionen für die Frage, falls zutreffend.
 * @property type Der Typ der Frage (z.B. Mehrfachauswahl, wahr/falsch).
 * @property answer Die richtige(n) Antwort(en) auf die Frage, repräsentiert als Indizes in der Liste [answerOptions].
 * @property explanation Eine optionale Erklärung oder zusätzliche Informationen zur Frage.
 */

data class Question(
    val questionText: String?,
    val answerOptions: List<String>?,
    val type: String?,
    val answer: List<Int>?,
    val explanation: String?,
)
