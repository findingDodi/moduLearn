package de.hsd.modulearn.data.module

data class Lecture(
    val id : Int,
    val title: String,
    val description: String,
    val chapters: List<Chapter>
)
