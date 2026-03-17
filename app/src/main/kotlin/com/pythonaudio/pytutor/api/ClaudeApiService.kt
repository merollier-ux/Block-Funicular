package com.pythonaudio.pytutor.api

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.pythonaudio.pytutor.model.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

class ClaudeApiService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val gson = Gson()

    // System prompt for the Python code executor role
    private val codeExecutorSystemPrompt = """
        You are an interactive Python interpreter built into a mobile learning app.
        When given Python code, simulate executing it exactly as Python would.

        Format your response as:
        **Output:**
        ```
        [exact output the code would produce]
        ```

        If there's an error, format as:
        **Error:** [error type]: [error message]
        **What went wrong:** [beginner-friendly explanation in 1-2 sentences]

        After showing output (or error), add:
        **Tip:** [one practical, encouraging tip related to the code concept — connect to audio/music processing when relevant]

        The learner is transitioning to AI/ML engineering specializing in audio processing and sound design.
        Keep explanations simple, encouraging, and practical.
    """.trimIndent()

    // System prompt for the AI tutor chat role
    private val tutorSystemPrompt = """
        You are PyAudio Tutor — a friendly, expert AI assistant helping someone learn Python
        for AI/ML engineering with a specialization in audio processing and sound design.

        Your student is a complete beginner transitioning their career. Background: they have
        experience in audio/sound design and want to apply Python to create intelligent audio tools.

        Your teaching style:
        - Use short, clear sentences. No jargon without explanation.
        - Connect Python concepts to audio examples whenever natural
          (e.g., "a list is like a playlist of sounds")
        - Be encouraging and celebrate small wins
        - Ask one follow-up question to keep them engaged
        - Keep responses to 3-5 sentences unless explaining a concept that needs more

        Career path you're guiding them toward:
        Python basics → NumPy arrays → Pandas data tables → Matplotlib charts →
        Librosa audio analysis → scikit-learn ML → Deep learning for audio (PyTorch/TensorFlow)
    """.trimIndent()

    suspend fun executeCode(code: String, apiKey: String): Result<String> =
        withContext(Dispatchers.IO) {
            makeApiCall(
                apiKey = apiKey,
                systemPrompt = codeExecutorSystemPrompt,
                messages = listOf(
                    ApiMessage(role = "user", content = "Execute this Python code:\n\n```python\n$code\n```")
                )
            )
        }

    suspend fun chat(messages: List<ChatMessage>, apiKey: String): Result<String> =
        withContext(Dispatchers.IO) {
            makeApiCall(
                apiKey = apiKey,
                systemPrompt = tutorSystemPrompt,
                messages = messages.map { ApiMessage(role = it.role, content = it.content) }
            )
        }

    suspend fun evaluateExercise(
        userCode: String,
        exercisePrompt: String,
        expectedConcept: String,
        apiKey: String
    ): Result<String> = withContext(Dispatchers.IO) {
        val systemPrompt = """
            You are evaluating a Python exercise for a beginner learner.
            Exercise: $exercisePrompt
            Concept being tested: $expectedConcept

            Evaluate the student's code and respond with:
            1. Whether it's correct (✅ Correct! or ❌ Not quite...)
            2. What they did well (1 sentence)
            3. If incorrect: what to fix (1-2 sentences, beginner-friendly)
            4. An audio/music analogy that relates to this concept (1 sentence)
            5. Their XP earned: either 50 XP (correct) or 10 XP (attempted)

            Keep it encouraging and brief.
        """.trimIndent()

        makeApiCall(
            apiKey = apiKey,
            systemPrompt = systemPrompt,
            messages = listOf(
                ApiMessage(role = "user", content = "Here is my code:\n\n```python\n$userCode\n```")
            )
        )
    }

    /**
     * Called when a learner is struggling. Returns a simplified breakdown of the concept
     * in small numbered steps with audio/music analogies throughout.
     */
    suspend fun generateBreakdown(
        lessonTitle: String,
        exercisePrompt: String,
        attemptCount: Int,
        apiKey: String
    ): Result<String> = withContext(Dispatchers.IO) {
        val systemPrompt = """
            You are a patient Python tutor helping a beginner who has tried $attemptCount times
            and is stuck. They are transitioning careers into audio/ML engineering.

            Break the concept "${lessonTitle}" into 3 super simple micro-steps:
            STEP 1: [simplest possible explanation, 1-2 sentences]
            STEP 2: [next building block, 1-2 sentences]
            STEP 3: [connecting it to the exercise, 1-2 sentences]

            🎵 AUDIO LINK: [one sentence connecting this to their audio/music background]

            Then give a SIMPLER version of the exercise that builds confidence:
            MINI CHALLENGE: [a much simpler version they can definitely complete]

            Be warm, use simple words, and celebrate that they're asking for help — that's smart!
        """.trimIndent()

        makeApiCall(
            apiKey = apiKey,
            systemPrompt = systemPrompt,
            messages = listOf(ApiMessage("user", "Exercise I'm stuck on: $exercisePrompt"))
        )
    }

    suspend fun getHint(
        exercisePrompt: String,
        userCode: String,
        hintNumber: Int,
        apiKey: String
    ): Result<String> = withContext(Dispatchers.IO) {
        makeApiCall(
            apiKey = apiKey,
            systemPrompt = "You are a helpful Python tutor. Give a hint numbered $hintNumber for the exercise. Be encouraging, not giving away the full answer. Keep it to 2-3 sentences.",
            messages = listOf(
                ApiMessage(
                    role = "user",
                    content = "Exercise: $exercisePrompt\n\nMy current code:\n```python\n$userCode\n```\n\nGive me hint #$hintNumber."
                )
            )
        )
    }

    private fun makeApiCall(
        apiKey: String,
        systemPrompt: String,
        messages: List<ApiMessage>
    ): Result<String> {
        if (apiKey.isBlank()) {
            return Result.failure(Exception("API key not set. Please add your Claude API key in Settings."))
        }

        return try {
            val requestBody = ApiRequest(
                model = "claude-opus-4-6",
                maxTokens = 1024,
                system = systemPrompt,
                messages = messages
            )

            val json = gson.toJson(requestBody)
            val body = json.toRequestBody("application/json".toMediaType())

            val request = Request.Builder()
                .url("https://api.anthropic.com/v1/messages")
                .post(body)
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .header("Content-Type", "application/json")
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string() ?: ""

            if (response.isSuccessful) {
                val apiResponse = gson.fromJson(responseBody, ApiResponse::class.java)
                val text = apiResponse.content.firstOrNull { it.type == "text" }?.text
                    ?: "No response received."
                Result.success(text)
            } else {
                val error = gson.fromJson(responseBody, ApiErrorResponse::class.java)
                Result.failure(Exception(error.error?.message ?: "API error: ${response.code}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // --- Data classes for JSON serialization ---

    private data class ApiRequest(
        val model: String,
        @SerializedName("max_tokens") val maxTokens: Int,
        val system: String,
        val messages: List<ApiMessage>
    )

    private data class ApiMessage(
        val role: String,
        val content: String
    )

    private data class ApiResponse(
        val content: List<ContentBlock>
    )

    private data class ContentBlock(
        val type: String,
        val text: String
    )

    private data class ApiErrorResponse(
        val error: ApiError?
    )

    private data class ApiError(
        val message: String
    )
}
