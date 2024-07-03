package de.hsd.modulearn.data

/**
 * Datenklasse, die ein Modul repräsentiert.
 *
 * @property title Der Titel oder Name des Moduls.
 * @property image Die Ressourcen-ID des Bildes, das das Modul repräsentiert.
 */

data class Module(
    val title: String,
    val image: Int
)
