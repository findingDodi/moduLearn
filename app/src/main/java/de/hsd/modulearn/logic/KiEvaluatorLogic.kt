package de.hsd.modulearn.logic

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

/**
 * Ruft eine Bewertung für eine Antwort vom KI-Modell basierend auf der bereitgestellten Antwort über die OpenAI API ab.
 *
 * @param answer Die Antwort oder Eingabe, die vom KI-Modell bewertet werden soll.
 * @return Die Bewertung des KI-Modells als Antwort, oder null, wenn ein Fehler auftritt.
 */
suspend fun getKiEvaluation(answer: String): String? {
    try {
        // Initialisierung des OpenAI Clients mit dem API-Schlüssel
        val apiKey = "sk-proj-QDQJklP5sWreTQl3PcqQT3BlbkFJmLw3YI9axNGbeXI0SzPd"
        val openAI = OpenAI(apiKey)

        // Erstellen der Chat-Vervollständigungsanfrage
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    // Kontext, der der KI übergeben wird
                    content = "Du bist ein Professor, der die Antworten von Studenten in einer " +
                            "Abschlussprüfung für das Modul Objektorientiere Programmierung bewertet. " +
                            "Du gibst ihnen hilfreiches Feedback auf ihre Antworten."
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = answer
                )
            )
        )

        // Ausführen der Chat-Vervollständigungsanfrage
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        val response = completion.choices.firstOrNull()?.message?.content

        return response
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}
