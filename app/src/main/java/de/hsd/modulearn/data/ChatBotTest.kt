package de.hsd.modulearn.data

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import de.hsd.modulearn.components.Header
import kotlinx.coroutines.launch

@Composable
fun ChatBotTest() {
    var response by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        response = getChat()
    }

    Box {
        Text(response ?: "Antwort des Chatbots laden...") // Anzeige während der Antwort geladen wird
    }
}

suspend fun getChat(): String? {
    val apiKey = "sk-proj-XWolcB0uKlWcTWMs5GkyT3BlbkFJ1z9DD2jpAqxPvmAWorME"
    val openAI = OpenAI(apiKey)

    val chatCompletionRequest = ChatCompletionRequest(
        model = ModelId("gpt-3.5-turbo"),
        messages = listOf(
            ChatMessage(
                role = ChatRole.System,
                content = "Du bist ein hilfreicher Chatbot, " +
                        "der Studenten aus dem ersten Semester " +
                        "hilft das Modul Objektoriente Programmierung zu erlernen"

            ),
            ChatMessage(
                role = ChatRole.User,
                content = "Was ist Programmieren?"
            )
        )
    )
    val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
    val response = completion.choices.first().message?.content

    return response
}