package de.hsd.modulearn.data.module

/**
 * Datenklasse, die eine Lektion innerhalb eines Moduls repräsentiert.
 *
 * @property id Die eindeutige Kennung der Vorlesung.
 * @property title Der Titel der Vorlesung.
 * @property chapters Die Liste der [Chapter]-Objekte, die die Kapitel innerhalb der Vorlesung repräsentieren.
 */
data class Lecture(
    val id : Int,
    val title: String,
    val chapters: List<Chapter>,
)
