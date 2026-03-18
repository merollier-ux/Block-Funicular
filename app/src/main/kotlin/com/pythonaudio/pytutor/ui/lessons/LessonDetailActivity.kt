package com.pythonaudio.pytutor.ui.lessons

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.pythonaudio.pytutor.api.ClaudeApiService
import com.pythonaudio.pytutor.data.LessonsRepository
import com.pythonaudio.pytutor.databinding.ActivityLessonDetailBinding
import com.pythonaudio.pytutor.model.Lesson
import com.pythonaudio.pytutor.model.LevelSystem
import com.pythonaudio.pytutor.util.AchievementManager
import com.pythonaudio.pytutor.util.LivesManager
import com.pythonaudio.pytutor.util.PreferencesManager
import kotlinx.coroutines.launch

class LessonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLessonDetailBinding
    private lateinit var prefs: PreferencesManager
    private lateinit var claudeService: ClaudeApiService
    private lateinit var lesson: Lesson

    // Session state
    private var sessionAttempts = 0
    private var breakdownShown = false
    private var hintIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = PreferencesManager(this)
        claudeService = ClaudeApiService()

        val lessonId = intent.getStringExtra(EXTRA_LESSON_ID) ?: run { finish(); return }
        lesson = LessonsRepository.getLessonById(lessonId) ?: run { finish(); return }

        LivesManager.regenIfNeeded(prefs)

        setupActionBar()
        displayLesson()
        setupButtons()
    }

    override fun onResume() {
        super.onResume()
        LivesManager.regenIfNeeded(prefs)
        updateLivesDisplay()
    }

    // ── UI setup ──────────────────────────────────────────────────────────────

    private fun setupActionBar() {
        supportActionBar?.apply {
            title = lesson.title
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun displayLesson() {
        binding.tvLessonTitle.text = "${lesson.icon} ${lesson.title}"
        binding.tvLessonSubtitle.text = lesson.subtitle
        binding.tvXpReward.text = "+${lesson.xpReward} XP"
        binding.tvEstimatedTime.text = "~${lesson.estimatedMinutes} min"

        binding.tvTheory.text = lesson.theory
            .replace("```python", "\n")
            .replace("```", "\n")

        binding.etCodeEditor.setText(lesson.codeExample)
        binding.tvExercisePrompt.text = "📝 Exercise: ${lesson.exercise.prompt}"
        binding.etExerciseCode.setText(lesson.exercise.starterCode)

        updateLivesDisplay()
        updateMultiplierBadge()
        updateAttemptInfo()

        if (prefs.isLessonCompleted(lesson.id)) {
            binding.btnMarkComplete.text = "✅ Completed"
            binding.btnMarkComplete.isEnabled = false
        }
    }

    private fun updateLivesDisplay() {
        val lives = prefs.lives
        val heartsStr = "❤️".repeat(lives) + "🖤".repeat(PreferencesManager.MAX_LIVES - lives)
        binding.tvLivesHeader.text = heartsStr
    }

    private fun updateMultiplierBadge() {
        when (sessionAttempts) {
            0 -> {
                binding.tvMultiplier.text = "⚡ 2x XP"
                binding.tvMultiplier.setTextColor(Color.parseColor("#FFD700"))
                binding.cardMultiplier.visibility = View.VISIBLE
            }
            1 -> {
                binding.tvMultiplier.text = "✨ 1.5x XP"
                binding.tvMultiplier.setTextColor(Color.parseColor("#C0C0C0"))
                binding.cardMultiplier.visibility = View.VISIBLE
            }
            else -> {
                binding.tvMultiplier.text = "1x XP"
                binding.tvMultiplier.setTextColor(Color.parseColor("#888888"))
                binding.cardMultiplier.visibility = View.VISIBLE
            }
        }
    }

    private fun updateAttemptInfo() {
        val attempt = sessionAttempts + 1
        binding.tvAttemptInfo.text = "Attempt $attempt"
        binding.tvFirstTryHint.visibility = if (sessionAttempts == 0) View.VISIBLE else View.GONE
    }

    // ── Button wiring ─────────────────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnRunExample.setOnClickListener {
            val code = binding.etCodeEditor.text.toString()
            if (code.isBlank()) {
                showSnack("Write some code first!")
                return@setOnClickListener
            }
            prefs.incrementCodeRuns()
            runCode(code, binding.tvExampleOutput)
        }

        binding.btnRunExercise.setOnClickListener {
            val code = binding.etExerciseCode.text.toString()
            if (code.isBlank()) {
                showSnack("Write some code first!")
                return@setOnClickListener
            }
            prefs.incrementCodeRuns()
            runCode(code, binding.tvExerciseOutput)
        }

        binding.btnSubmitExercise.setOnClickListener {
            val code = binding.etExerciseCode.text.toString()
            if (code.isBlank()) {
                showSnack("Write your solution first!")
                return@setOnClickListener
            }
            evaluateExercise(code)
        }

        binding.btnHint.setOnClickListener {
            val hints = lesson.exercise.hints
            if (hintIndex < hints.size) {
                prefs.incrementHints(lesson.id)
                binding.tvHint.text = "💡 Hint ${hintIndex + 1}: ${hints[hintIndex]}"
                binding.tvHint.visibility = View.VISIBLE
                hintIndex++
                if (hintIndex >= hints.size) {
                    binding.btnHint.text = "No more hints"
                    binding.btnHint.isEnabled = false
                }
            } else {
                // Use Claude for AI hints if hardcoded hints exhausted
                fetchAiHint()
            }
        }

        binding.btnShowSolution.setOnClickListener {
            binding.tvSolution.text = "📖 Solution:\n\n${lesson.exercise.solution}"
            binding.tvSolution.visibility = View.VISIBLE
            binding.btnShowSolution.isEnabled = false
        }

        binding.btnMarkComplete.setOnClickListener {
            if (!prefs.isLessonCompleted(lesson.id)) {
                val xp = lesson.xpReward
                prefs.markLessonCompleted(lesson.id, xp)
                binding.btnMarkComplete.text = "✅ Completed!"
                binding.btnMarkComplete.isEnabled = false
                showSnack("+$xp XP earned! 🎉")
            }
        }

        binding.btnBreakdown.setOnClickListener {
            fetchBreakdown()
        }
    }

    // ── Core logic ────────────────────────────────────────────────────────────

    private fun runCode(code: String, outputView: android.widget.TextView) {
        val apiKey = prefs.apiKey
        if (apiKey.isBlank()) {
            outputView.text = "⚠️ Please add your Claude API key in Settings to run code."
            return
        }

        outputView.text = "⏳ Running with Claude..."
        setUiEnabled(false)

        lifecycleScope.launch {
            val result = claudeService.executeCode(code, apiKey)
            setUiEnabled(true)
            result.fold(
                onSuccess = { response ->
                    outputView.text = response
                    // Track error fixes
                    if (response.contains("Error:", ignoreCase = true) ||
                        response.contains("Traceback", ignoreCase = true)
                    ) {
                        // Not an error fix yet — they ran code with an error
                    } else if (outputView.id == binding.tvExerciseOutput.id) {
                        prefs.incrementErrorsFixed()
                    }
                },
                onFailure = { error -> outputView.text = "❌ Error: ${error.message}" }
            )
        }
    }

    private fun evaluateExercise(code: String) {
        val apiKey = prefs.apiKey
        if (apiKey.isBlank()) {
            binding.tvExerciseOutput.text = "⚠️ Please add your Claude API key in Settings to get feedback."
            return
        }

        binding.tvExerciseOutput.text = "⏳ Claude is evaluating your solution..."
        setUiEnabled(false)

        lifecycleScope.launch {
            val result = claudeService.evaluateExercise(
                userCode = code,
                exercisePrompt = lesson.exercise.prompt,
                expectedConcept = lesson.tags.joinToString(", "),
                apiKey = apiKey
            )
            setUiEnabled(true)
            result.fold(
                onSuccess = { response ->
                    binding.tvExerciseOutput.text = response
                    val isCorrect = response.contains("✅") ||
                        response.contains("Correct", ignoreCase = true)

                    if (isCorrect) {
                        onExerciseSuccess()
                    } else {
                        onExerciseFailure()
                    }
                },
                onFailure = { error ->
                    binding.tvExerciseOutput.text = "❌ Error: ${error.message}"
                }
            )
        }
    }

    private fun onExerciseSuccess() {
        if (prefs.isLessonCompleted(lesson.id)) return

        val multiplier = when (sessionAttempts) {
            0 -> 2.0f
            1 -> 1.5f
            else -> 1.0f
        }
        val earnedXp = (lesson.xpReward * multiplier).toInt()

        // Track first-try bonus
        if (sessionAttempts == 0) {
            prefs.setFirstTry(lesson.id)
        }

        val xpBefore = prefs.totalXp
        val levelBefore = LevelSystem.forXp(xpBefore)
        prefs.markLessonCompleted(lesson.id, earnedXp)
        val levelAfter = LevelSystem.forXp(prefs.totalXp)

        // Check achievements
        val newAchievements = AchievementManager.checkAll(prefs)

        // Update UI
        binding.btnMarkComplete.text = "✅ Completed!"
        binding.btnMarkComplete.isEnabled = false

        val multiplierStr = when (sessionAttempts) {
            0 -> " (2x first-try bonus! ⚡)"
            1 -> " (1.5x bonus! ✨)"
            else -> ""
        }
        showSnack("+$earnedXp XP earned$multiplierStr 🎉")

        // Level-up celebration
        if (levelAfter.number > levelBefore.number) {
            lifecycleScope.launch {
                kotlinx.coroutines.delay(1500)
                showSnack("🎉 LEVEL UP! You're now ${levelAfter.emoji} ${levelAfter.title}!")
            }
        }

        // Achievement unlocks
        if (newAchievements.isNotEmpty()) {
            newAchievements.forEachIndexed { index, achievement ->
                lifecycleScope.launch {
                    kotlinx.coroutines.delay((2000 + index * 1200).toLong())
                    showSnack("🏆 Achievement unlocked: ${achievement.icon} ${achievement.title} (+${achievement.xpReward} XP)")
                }
            }
        }
    }

    private fun onExerciseFailure() {
        sessionAttempts++
        prefs.incrementAttempts(lesson.id)
        updateMultiplierBadge()
        updateAttemptInfo()

        if (sessionAttempts >= 2 && !breakdownShown) {
            breakdownShown = true
            binding.cardBreakdown.visibility = View.VISIBLE
        }

        if (sessionAttempts >= 3) {
            val lifeUsed = LivesManager.useLife(prefs)
            updateLivesDisplay()
            prefs.setStruggled(lesson.id)

            if (!lifeUsed) {
                showSnack("💔 No lives left! You're still learning — keep going!")
            } else {
                val remaining = prefs.lives
                val msg = if (remaining > 0) {
                    "💔 Lost a life! ${"❤️".repeat(remaining)} remaining"
                } else {
                    "💔 Out of lives! Lives regen every 30 minutes."
                }
                showSnack(msg)
            }
        }
    }

    private fun fetchBreakdown() {
        val apiKey = prefs.apiKey
        if (apiKey.isBlank()) {
            binding.tvBreakdownContent.text = "⚠️ Add your Claude API key in Settings to use this feature."
            binding.tvBreakdownContent.visibility = View.VISIBLE
            return
        }

        binding.btnBreakdown.isEnabled = false
        binding.tvBreakdownContent.text = "⏳ Claude is breaking it down for you..."
        binding.tvBreakdownContent.visibility = View.VISIBLE

        lifecycleScope.launch {
            val result = claudeService.generateBreakdown(
                lessonTitle = lesson.title,
                exercisePrompt = lesson.exercise.prompt,
                attemptCount = sessionAttempts,
                apiKey = apiKey
            )
            result.fold(
                onSuccess = { response ->
                    binding.tvBreakdownContent.text = response
                },
                onFailure = { error ->
                    binding.tvBreakdownContent.text = "❌ Error: ${error.message}"
                    binding.btnBreakdown.isEnabled = true
                }
            )
        }
    }

    private fun fetchAiHint() {
        val apiKey = prefs.apiKey
        if (apiKey.isBlank()) return

        prefs.incrementHints(lesson.id)
        binding.tvHint.text = "💡 Getting AI hint..."
        binding.tvHint.visibility = View.VISIBLE

        lifecycleScope.launch {
            val result = claudeService.getHint(
                exercisePrompt = lesson.exercise.prompt,
                userCode = binding.etExerciseCode.text.toString(),
                hintNumber = hintIndex + 1,
                apiKey = apiKey
            )
            result.fold(
                onSuccess = { response -> binding.tvHint.text = "💡 $response" },
                onFailure = { error -> binding.tvHint.text = "❌ ${error.message}" }
            )
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun setUiEnabled(enabled: Boolean) {
        binding.btnRunExample.isEnabled = enabled
        binding.btnRunExercise.isEnabled = enabled
        binding.btnSubmitExercise.isEnabled = enabled
    }

    private fun showSnack(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_LESSON_ID = "lesson_id"
    }
}
