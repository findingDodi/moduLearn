package de.hsd.modulearn.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppContext {
    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    fun getContext(): Context {
        if (!this::context.isInitialized) {
            throw IllegalStateException("Context is not initialized. Call initialize(context) first.")
        }

        return context
    }
}
