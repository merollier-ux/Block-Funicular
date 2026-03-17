package com.pythonaudio.pytutor.ui.codelab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.pythonaudio.pytutor.api.ClaudeApiService
import com.pythonaudio.pytutor.databinding.FragmentCodeLabBinding
import com.pythonaudio.pytutor.util.PreferencesManager
import kotlinx.coroutines.launch

class CodeLabFragment : Fragment() {

    private var _binding: FragmentCodeLabBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefsManager: PreferencesManager
    private val claudeService = ClaudeApiService()

    private val codeSnippets = listOf(
        "# Hello World\nprint('Hello, Python!')\nprint('I am learning to code!')",
        "# Variables and math\nfrequency = 440\nwavelength = 343 / frequency\nprint(f'A4 wavelength: {wavelength:.4f} meters')",
        "# Lists\nnotes = ['C', 'D', 'E', 'F', 'G', 'A', 'B']\nfor note in notes:\n    print(f'Note: {note}')",
        "# Functions\ndef midi_to_hz(midi_note):\n    return 440 * (2 ** ((midi_note - 69) / 12))\n\nfor midi in range(60, 73):\n    print(f'MIDI {midi}: {midi_to_hz(midi):.2f} Hz')",
        "# NumPy sine wave\nimport numpy as np\nsr = 44100\nt = np.linspace(0, 0.01, 441)\nwave = np.sin(2 * np.pi * 440 * t)\nprint('First 5 samples:', np.round(wave[:5], 4))\nprint('Max:', np.max(wave))"
    )

    private var currentSnippetIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeLabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefsManager = PreferencesManager(requireContext())
        setupUI()
        loadSnippet(codeSnippets[0])
    }

    private fun setupUI() {
        // Run button
        binding.btnRun.setOnClickListener {
            val code = binding.etCode.text.toString()
            if (code.isBlank()) {
                Toast.makeText(requireContext(), "Write some code first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            executeCode(code)
        }

        // Clear button
        binding.btnClear.setOnClickListener {
            binding.etCode.setText("")
            binding.tvOutput.text = "Output will appear here..."
        }

        // Explain button
        binding.btnExplain.setOnClickListener {
            val code = binding.etCode.text.toString()
            if (code.isBlank()) {
                Toast.makeText(requireContext(), "Write some code to explain!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            explainCode(code)
        }

        // Snippet navigation
        binding.btnPrevSnippet.setOnClickListener {
            currentSnippetIndex = (currentSnippetIndex - 1 + codeSnippets.size) % codeSnippets.size
            loadSnippet(codeSnippets[currentSnippetIndex])
        }

        binding.btnNextSnippet.setOnClickListener {
            currentSnippetIndex = (currentSnippetIndex + 1) % codeSnippets.size
            loadSnippet(codeSnippets[currentSnippetIndex])
        }

        // Template buttons
        binding.chipBasics.setOnClickListener { loadSnippet(codeSnippets[0]) }
        binding.chipVariables.setOnClickListener { loadSnippet(codeSnippets[1]) }
        binding.chipLoops.setOnClickListener { loadSnippet(codeSnippets[2]) }
        binding.chipFunctions.setOnClickListener { loadSnippet(codeSnippets[3]) }
        binding.chipNumpy.setOnClickListener { loadSnippet(codeSnippets[4]) }
    }

    private fun loadSnippet(code: String) {
        binding.etCode.setText(code)
        binding.tvOutput.text = "Output will appear here..."
        binding.tvSnippetCounter.text = "${currentSnippetIndex + 1} / ${codeSnippets.size}"
    }

    private fun executeCode(code: String) {
        val apiKey = prefsManager.apiKey
        if (apiKey.isBlank()) {
            binding.tvOutput.text = "⚠️ API key required!\n\nGo to Settings (top-right menu) and add your Claude API key to run Python code."
            return
        }

        prefsManager.incrementCodeRuns()
        binding.tvOutput.text = "⏳ Running your code..."
        setButtonsEnabled(false)

        lifecycleScope.launch {
            val result = claudeService.executeCode(code, apiKey)
            setButtonsEnabled(true)
            result.fold(
                onSuccess = { binding.tvOutput.text = it },
                onFailure = { binding.tvOutput.text = "❌ ${it.message}" }
            )
        }
    }

    private fun explainCode(code: String) {
        val apiKey = prefsManager.apiKey
        if (apiKey.isBlank()) {
            binding.tvOutput.text = "⚠️ API key required to get explanations."
            return
        }

        binding.tvOutput.text = "⏳ Claude is analyzing your code..."
        setButtonsEnabled(false)

        lifecycleScope.launch {
            val prompt = "Explain this Python code to a beginner in 3-4 sentences. Connect it to audio/music concepts if relevant:\n\n```python\n$code\n```"
            val result = claudeService.chat(
                messages = listOf(com.pythonaudio.pytutor.model.ChatMessage("user", prompt)),
                apiKey = apiKey
            )
            setButtonsEnabled(true)
            result.fold(
                onSuccess = { binding.tvOutput.text = "📖 Explanation:\n\n$it" },
                onFailure = { binding.tvOutput.text = "❌ ${it.message}" }
            )
        }
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        binding.btnRun.isEnabled = enabled
        binding.btnExplain.isEnabled = enabled
        if (enabled) {
            binding.btnRun.text = "▶ Run"
        } else {
            binding.btnRun.text = "⏳ Running..."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
