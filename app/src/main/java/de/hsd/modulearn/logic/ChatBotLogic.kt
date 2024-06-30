package de.hsd.modulearn.logic

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

suspend fun getChat(question: String): String? {
    try {

        val apiKey = "sk-proj-GhxeqdvDTIP8ncV3hvdpT3BlbkFJTVEocu1JfdDeqAcZKg4K"
        val openAI = OpenAI(apiKey)

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "Du bist ein hilfreicher Chatbot, " +
                            "der Studenten aus dem ersten Semester " +
                            "hilft das Modul Objektorientierte Programmierung zu erlernen"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = question
                )
            )
        )
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        val response = completion.choices.firstOrNull()?.message?.content

        return response
    } catch (e: Exception) {
        println("Fehler bei der Anfrage an OpenAI API: ${e.message}")
        e.printStackTrace()
        return null
    }
}
