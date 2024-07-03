package de.hsd.modulearn.utils

import android.annotation.SuppressLint
import android.content.Context

/**
 * Eine Singleton-Klasse zur Verwaltung der Anwendungs-Kontextreferenz.
 */
@SuppressLint("StaticFieldLeak")
object AppContext {
    private lateinit var context: Context

    /**
     * Initialisiert den Anwendungs-Kontext.
     *
     * @param context Der Anwendungs-Kontext, der initialisiert werden soll.
     */
    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    /**
     * Gibt den gespeicherten Anwendungs-Kontext zurück.
     *
     * @return Der gespeicherte Anwendungs-Kontext.
     * @throws IllegalStateException Falls der Kontext nicht initialisiert wurde. Rufen Sie zuerst `initialize(context)` auf.
     */
    fun getContext(): Context {
        if (!this::context.isInitialized) {
            throw IllegalStateException("Context is not initialized. Call initialize(context) first.")
        }

        return context
    }
}
