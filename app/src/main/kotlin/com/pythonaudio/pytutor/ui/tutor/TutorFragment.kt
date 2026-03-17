package com.pythonaudio.pytutor.ui.tutor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pythonaudio.pytutor.api.ClaudeApiService
import com.pythonaudio.pytutor.databinding.FragmentTutorBinding
import com.pythonaudio.pytutor.model.ChatMessage
import com.pythonaudio.pytutor.util.PreferencesManager
import kotlinx.coroutines.launch

class TutorFragment : Fragment() {

    private var _binding: FragmentTutorBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefsManager: PreferencesManager
    private val claudeService = ClaudeApiService()
    private val messages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    private val quickPrompts = listOf(
        "What should I learn first in Python?",
        "Explain variables with a music example",
        "What is a for loop?",
        "How does Python relate to audio processing?",
        "What is NumPy used for?",
        "How do I get started with Librosa?"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefsManager = PreferencesManager(requireContext())
        setupRecyclerView()
        setupInput()
        setupQuickPrompts()

        // Welcome message
        if (messages.isEmpty()) {
            addAssistantMessage(
                "Hi! I'm your PyAudio Tutor 🎵🐍\n\n" +
                "I'm here to help you learn Python for AI/ML engineering " +
                "with a focus on audio and sound design.\n\n" +
                "What would you like to learn today? You can ask me anything — " +
                "from basic Python syntax to how neural networks process audio!"
            )
        }
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(messages)
        binding.recyclerChat.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true
            }
            adapter = chatAdapter
        }
    }

    private fun setupInput() {
        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString().trim()
            if (text.isBlank()) return@setOnClickListener

            val apiKey = prefsManager.apiKey
            if (apiKey.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Add your Claude API key in Settings to chat!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            sendMessage(text, apiKey)
        }

        binding.btnClearChat.setOnClickListener {
            messages.clear()
            chatAdapter.notifyDataSetChanged()
            addAssistantMessage(
                "Chat cleared! Ready to continue learning. What's on your mind? 🎵"
            )
        }
    }

    private fun setupQuickPrompts() {
        val chips = listOf(
            binding.chipPrompt1, binding.chipPrompt2,
            binding.chipPrompt3, binding.chipPrompt4
        )

        chips.forEachIndexed { index, chip ->
            if (index < quickPrompts.size) {
                chip.text = quickPrompts[index]
                chip.setOnClickListener {
                    binding.etMessage.setText(quickPrompts[index])
                }
            }
        }
    }

    private fun sendMessage(text: String, apiKey: String) {
        // Add user message
        messages.add(ChatMessage(role = "user", content = text))
        chatAdapter.notifyItemInserted(messages.size - 1)
        binding.recyclerChat.scrollToPosition(messages.size - 1)
        binding.etMessage.setText("")

        // Show typing indicator
        binding.btnSend.isEnabled = false
        binding.tvTypingIndicator.visibility = View.VISIBLE

        lifecycleScope.launch {
            // Only pass last 20 messages to keep context manageable
            val contextMessages = messages.takeLast(20)
            val result = claudeService.chat(contextMessages, apiKey)

            binding.btnSend.isEnabled = true
            binding.tvTypingIndicator.visibility = View.GONE

            result.fold(
                onSuccess = { response -> addAssistantMessage(response) },
                onFailure = { error ->
                    addAssistantMessage("❌ Error: ${error.message}\n\nPlease check your API key in Settings.")
                }
            )
        }
    }

    private fun addAssistantMessage(text: String) {
        messages.add(ChatMessage(role = "assistant", content = text))
        chatAdapter.notifyItemInserted(messages.size - 1)
        binding.recyclerChat.smoothScrollToPosition(messages.size - 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
