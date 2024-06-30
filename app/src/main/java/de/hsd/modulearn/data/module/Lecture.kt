package de.hsd.modulearn.data.module

import android.icu.text.Transliterator.Position

data class Lecture(
    val id : Int,
    val title: String,
    val description: String,
    val chapters: List<Chapter>,
)
