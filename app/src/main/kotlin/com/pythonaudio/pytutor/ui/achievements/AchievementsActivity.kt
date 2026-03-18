package com.pythonaudio.pytutor.ui.achievements

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pythonaudio.pytutor.databinding.ActivityAchievementsBinding
import com.pythonaudio.pytutor.model.AchievementsRepository
import com.pythonaudio.pytutor.util.PreferencesManager

class AchievementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAchievementsBinding
    private lateinit var prefs: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Achievements"
            setDisplayHomeAsUpEnabled(true)
        }

        prefs = PreferencesManager(this)
        setupList()
    }

    override fun onResume() {
        super.onResume()
        setupList()
    }

    private fun setupList() {
        val all = AchievementsRepository.all
        val unlockedIds = all.filter { prefs.isAchievementUnlocked(it.id) }.map { it.id }.toSet()
        val total = all.size
        val unlocked = unlockedIds.size

        binding.tvUnlockedCount.text = "$unlocked / $total unlocked"
        binding.progressAchievements.progress = if (total > 0) (unlocked * 100) / total else 0

        // Sort: unlocked first, then by rarity descending
        val sorted = all.sortedWith(
            compareByDescending<com.pythonaudio.pytutor.model.Achievement> { it.id in unlockedIds }
                .thenByDescending { it.rarity.ordinal }
        )

        binding.rvAchievements.layoutManager = LinearLayoutManager(this)
        binding.rvAchievements.adapter = AchievementsAdapter(sorted, unlockedIds)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
