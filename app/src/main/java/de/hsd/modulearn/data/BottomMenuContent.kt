package de.hsd.modulearn.data

import androidx.annotation.DrawableRes

data class BottomMenuContent(
    val title: String,
    val screen: String,
    @DrawableRes val iconId: Int
)
