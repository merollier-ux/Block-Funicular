package com.pythonaudio.pytutor.ui.lessons

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pythonaudio.pytutor.api.ClaudeApiService
import com.pythonaudio.pytutor.data.LessonsRepository
import com.pythonaudio.pytutor.databinding.ActivityLessonDetailBinding
import com.pythonaudio.pytutor.model.Lesson
import com.pythonaudio.pytutor.util.PreferencesManager
import kotlinx.coroutines.launch

class LessonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLessonDetailBinding
    private lateinit var prefsManager: PreferencesManager
    private lateinit var claudeService: ClaudeApiService
    private lateinit var lesson: Lesson
    private var hintIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefsManager = PreferencesManager(this)
        claudeService = ClaudeApiService()

        val lessonId = intent.getStringExtra(EXTRA_LESSON_ID) ?: run {
            finish()
            return
        }

        lesson = LessonsRepository.getLessonById(lessonId) ?: run {
            finish()
            return
        }

        setupActionBar()
        displayLesson()
        setupButtons()
    }

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

        // Render theory markdown using simple text for now
        binding.tvTheory.text = lesson.theory
            .replace("```python", "\n")
            .replace("```", "\n")

        // Pre-fill code editor with example
        binding.etCodeEditor.setText(lesson.codeExample)

        // Exercise
        binding.tvExercisePrompt.text = "📝 Exercise: ${lesson.exercise.prompt}"
        binding.etExerciseCode.setText(lesson.exercise.starterCode)

        // Mark completed if already done
        if (prefsManager.isLessonCompleted(lesson.id)) {
            binding.btnMarkComplete.text = "✅ Completed"
            binding.btnMarkComplete.isEnabled = false
        }
    }

    private fun setupButtons() {
        // Run example code through Claude
        binding.btnRunExample.setOnClickListener {
            val code = binding.etCodeEditor.text.toString()
            if (code.isBlank()) {
                Toast.makeText(this, "Please write some code first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            runCode(code, binding.tvExampleOutput)
        }

        // Run exercise code through Claude
        binding.btnRunExercise.setOnClickListener {
            val code = binding.etExerciseCode.text.toString()
            if (code.isBlank()) {
                Toast.makeText(this, "Please write some code first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            runCode(code, binding.tvExerciseOutput)
        }

        // Submit exercise for evaluation
        binding.btnSubmitExercise.setOnClickListener {
            val code = binding.etExerciseCode.text.toString()
            if (code.isBlank()) {
                Toast.makeText(this, "Write your solution first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            evaluateExercise(code)
        }

        // Hint button
        binding.btnHint.setOnClickListener {
            val hints = lesson.exercise.hints
            if (hintIndex < hints.size) {
                binding.tvHint.text = "💡 Hint ${hintIndex + 1}: ${hints[hintIndex]}"
                binding.tvHint.visibility = View.VISIBLE
                hintIndex++
                if (hintIndex >= hints.size) {
                    binding.btnHint.text = "No more hints"
                    binding.btnHint.isEnabled = false
                }
            }
        }

        // Show solution
        binding.btnShowSolution.setOnClickListener {
            binding.tvSolution.text = "📖 Solution:\n\n${lesson.exercise.solution}"
            binding.tvSolution.visibility = View.VISIBLE
            binding.btnShowSolution.isEnabled = false
        }

        // Mark lesson complete
        binding.btnMarkComplete.setOnClickListener {
            prefsManager.markLessonCompleted(lesson.id, lesson.xpReward)
            binding.btnMarkComplete.text = "✅ Completed!"
            binding.btnMarkComplete.isEnabled = false
            Toast.makeText(this, "+${lesson.xpReward} XP earned! 🎉", Toast.LENGTH_LONG).show()
        }
    }

    private fun runCode(code: String, outputView: android.widget.TextView) {
        val apiKey = prefsManager.apiKey
        if (apiKey.isBlank()) {
            outputView.text = "⚠️ Please add your Claude API key in Settings (top-right menu) to run code."
            return
        }

        outputView.text = "⏳ Running with Claude..."
        setUiEnabled(false)

        lifecycleScope.launch {
            val result = claudeService.executeCode(code, apiKey)
            setUiEnabled(true)
            result.fold(
                onSuccess = { response -> outputView.text = response },
                onFailure = { error -> outputView.text = "❌ Error: ${error.message}" }
            )
        }
    }

    private fun evaluateExercise(code: String) {
        val apiKey = prefsManager.apiKey
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
                    // Auto-mark complete if response contains success indicator
                    if (response.contains("✅") || response.contains("Correct")) {
                        if (!prefsManager.isLessonCompleted(lesson.id)) {
                            prefsManager.markLessonCompleted(lesson.id, lesson.xpReward)
                            binding.btnMarkComplete.text = "✅ Completed!"
                            binding.btnMarkComplete.isEnabled = false
                            Toast.makeText(
                                this@LessonDetailActivity,
                                "+${lesson.xpReward} XP earned! 🎉",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                },
                onFailure = { error ->
                    binding.tvExerciseOutput.text = "❌ Error: ${error.message}"
                }
            )
        }
    }

    private fun setUiEnabled(enabled: Boolean) {
        binding.btnRunExample.isEnabled = enabled
        binding.btnRunExercise.isEnabled = enabled
        binding.btnSubmitExercise.isEnabled = enabled
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_LESSON_ID = "lesson_id"
    }
}
