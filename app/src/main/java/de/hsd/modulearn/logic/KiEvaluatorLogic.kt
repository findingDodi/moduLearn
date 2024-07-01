package de.hsd.modulearn.logic

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

suspend fun getKiEvaluation(answer: String): String? {
    try {
        val apiKey = "sk-proj-uCBnRThQfLnji7JDdoXIT3BlbkFJGy366XTSrKOxJi50UgsG"
        val openAI = OpenAI(apiKey)

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
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
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        val response = completion.choices.firstOrNull()?.message?.content

        return response
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}
