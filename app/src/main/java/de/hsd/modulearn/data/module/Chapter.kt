package de.hsd.modulearn.data.module

/**
 * Datenklasse, die ein Kapitel innerhalb eines Moduls repräsentiert.
 *
 * @property title Der Titel des Kapitels.
 * @property content Der Inhalt oder die Beschreibung des Kapitels.
 */
data class Chapter(
    val title : String,
    val content: String
)
