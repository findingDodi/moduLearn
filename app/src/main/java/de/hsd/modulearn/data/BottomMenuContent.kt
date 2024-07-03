package de.hsd.modulearn.data

/**
 * Datenklasse, die Inhalte für Elemente in einer Bottom-Navigationsleiste repräsentiert.
 *
 * @property title Der Titel oder das Label des Menüelements.
 * @property screen Die Zielseite, die mit dem Menüelement verknüpft ist.
 * @property icon Die Ressourcen-ID des Icons, das mit dem Menüelement verknüpft ist.
 */

data class BottomMenuContent(
    val title: String,
    val screen: String,
    val icon: Int
)
