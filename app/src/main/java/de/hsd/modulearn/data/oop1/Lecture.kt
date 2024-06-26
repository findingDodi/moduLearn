package de.hsd.modulearn.data.oop1

data class Lecture(
    val id : Int,
    val title: String,
    val description: String,
    val chapters: List<Chapter>
)
