package com.pythonaudio.pytutor.ui.settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pythonaudio.pytutor.databinding.ActivityApiKeyBinding
import com.pythonaudio.pytutor.util.PreferencesManager

class ApiKeyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiKeyBinding
    private lateinit var prefsManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefsManager = PreferencesManager(this)

        supportActionBar?.apply {
            title = "API Key Settings"
            setDisplayHomeAsUpEnabled(true)
        }

        // Show current key (masked)
        val currentKey = prefsManager.apiKey
        if (currentKey.isNotBlank()) {
            val masked = "sk-ant-..." + currentKey.takeLast(8)
            binding.tvCurrentKey.text = "Current key: $masked"
        } else {
            binding.tvCurrentKey.text = "No API key set"
        }

        binding.btnSaveKey.setOnClickListener {
            val newKey = binding.etApiKey.text.toString().trim()
            if (newKey.isBlank()) {
                Toast.makeText(this, "Please enter an API key", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!newKey.startsWith("sk-ant-")) {
                Toast.makeText(this, "Invalid key format. Should start with sk-ant-", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            prefsManager.apiKey = newKey
            Toast.makeText(this, "API key saved! ✅", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnClearKey.setOnClickListener {
            prefsManager.apiKey = ""
            binding.tvCurrentKey.text = "No API key set"
            binding.etApiKey.setText("")
            Toast.makeText(this, "API key cleared", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
