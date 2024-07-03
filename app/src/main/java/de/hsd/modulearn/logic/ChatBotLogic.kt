package de.hsd.modulearn.logic

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

/**
 * Ruft eine Chatbot-Antwort basierend auf der bereitgestellten Frage über die OpenAI API ab.
 *
 * @param question Die Frage oder der Eingabetext für den Chatbot.
 * @return Die Antwort des Chatbots oder null, wenn ein Fehler aufgetreten ist.
 */
suspend fun getChat(question: String): String? {
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
                    content = "Du bist ein hilfreicher Chatbot, " +
                            "der Studierende aus dem ersten Semester " +
                            "hilft das Modul Objektorientierte Programmierung zu erlernen"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = question
                )
            )
        )
        // Ausführen der Chat-Vervollständigungsanfrage
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        val response = completion.choices.firstOrNull()?.message?.content

        return response
    } catch (e: Exception) {
        println("Fehler bei der Anfrage an OpenAI API: ${e.message}")
        e.printStackTrace()
        return null
    }
}
