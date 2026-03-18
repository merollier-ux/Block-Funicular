package com.pythonaudio.pytutor.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pythonaudio.pytutor.data.LessonsRepository
import com.pythonaudio.pytutor.databinding.FragmentHomeBinding
import com.pythonaudio.pytutor.model.AchievementsRepository
import com.pythonaudio.pytutor.model.LevelSystem
import com.pythonaudio.pytutor.ui.achievements.AchievementsActivity
import com.pythonaudio.pytutor.ui.lessons.LessonDetailActivity
import com.pythonaudio.pytutor.ui.settings.ApiKeyActivity
import com.pythonaudio.pytutor.util.LivesManager
import com.pythonaudio.pytutor.util.PreferencesManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs: PreferencesManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = PreferencesManager(requireContext())
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        LivesManager.regenIfNeeded(prefs)
        refreshUI()
    }

    private fun refreshUI() {
        val xp           = prefs.totalXp
        val streak       = prefs.currentStreak
        val completedCount = prefs.getCompletedLessonCount()
        val totalLessons = LessonsRepository.getAllLessons().size
        val level        = LevelSystem.forXp(xp)
        val lives        = prefs.lives

        // ── Level card ────────────────────────────────────────────────────
        binding.tvLevelBadge.text  = "LVL ${level.number}"
        binding.tvLevelTitle.text  = "${level.emoji} ${level.title}"
        binding.tvStreakBadge.text = "🔥 $streak"
        binding.tvXpAmount.text    = "$xp XP"

        val xpToNext = LevelSystem.xpToNext(xp)
        binding.tvXpToNext.text = if (xpToNext > 0) "$xpToNext XP to next level" else "MAX LEVEL 🎵"
        binding.progressXp.progress = (LevelSystem.progressFraction(xp) * 100).toInt()

        // ── Lives display ─────────────────────────────────────────────────
        val heartsStr = "❤️".repeat(lives) + "🖤".repeat(PreferencesManager.MAX_LIVES - lives)
        binding.tvLives.text = heartsStr
        if (lives < PreferencesManager.MAX_LIVES) {
            val mins = LivesManager.minutesUntilNext(prefs)
            binding.tvLivesRegen.text = "+1 in ${mins}m"
            binding.tvLivesRegen.visibility = View.VISIBLE
        } else {
            binding.tvLivesRegen.visibility = View.GONE
        }

        // ── API key warning ───────────────────────────────────────────────
        binding.cardApiWarning.visibility = if (!prefs.hasApiKey()) View.VISIBLE else View.GONE

        // ── Continue card ─────────────────────────────────────────────────
        val nextLesson = LessonsRepository.getAllLessons()
            .firstOrNull { !prefs.isLessonCompleted(it.id) }
        if (nextLesson != null) {
            binding.tvNextLessonIcon.text  = nextLesson.icon
            binding.tvNextLessonTitle.text = nextLesson.title
            binding.tvNextLessonMeta.text  = "+${nextLesson.xpReward} XP · ~${nextLesson.estimatedMinutes} min"
        } else {
            binding.tvNextLessonTitle.text = "All lessons complete! 🎓"
            binding.tvNextLessonMeta.text  = "You've mastered the curriculum"
            binding.tvNextLessonIcon.text  = "🎓"
        }

        // ── Journey progress ──────────────────────────────────────────────
        val pct = if (totalLessons > 0) (completedCount * 100) / totalLessons else 0
        binding.progressLessons.progress = pct
        binding.tvProgressFraction.text = "$completedCount / $totalLessons"
        binding.tvProgressLabel.text = "$pct% of your Python → Audio ML journey complete"

        // ── Achievements preview ──────────────────────────────────────────
        val unlockedCount = prefs.getUnlockedCount()
        val totalAch = AchievementsRepository.all.size
        binding.tvAchievementCount.text = "$unlockedCount / $totalAch"

        binding.achievementsPreview.removeAllViews()
        val unlockedAch = AchievementsRepository.all.filter { prefs.isAchievementUnlocked(it.id) }.take(8)
        val lockedSlots = (8 - unlockedAch.size).coerceAtLeast(0)
        for (ach in unlockedAch) {
            addAchievementIcon(ach.icon, false)
        }
        repeat(lockedSlots) { addAchievementIcon("🔒", true) }

        // ── Stats ─────────────────────────────────────────────────────────
        binding.tvStatLessons.text   = completedCount.toString()
        binding.tvStatCodeRuns.text  = prefs.totalCodeRuns.toString()
        binding.tvStatStreak.text    = streak.toString()
    }

    private fun addAchievementIcon(emoji: String, locked: Boolean) {
        val tv = TextView(requireContext()).apply {
            text      = emoji
            textSize  = 24f
            alpha     = if (locked) 0.3f else 1f
            val dp8   = (8 * resources.displayMetrics.density).toInt()
            setPadding(dp8 / 2, 0, dp8 / 2, 0)
        }
        binding.achievementsPreview.addView(tv)
    }

    private fun setupClickListeners() {
        binding.btnAddApiKey.setOnClickListener {
            startActivity(Intent(requireContext(), ApiKeyActivity::class.java))
        }

        binding.cardContinue.setOnClickListener {
            val next = LessonsRepository.getAllLessons()
                .firstOrNull { !prefs.isLessonCompleted(it.id) } ?: return@setOnClickListener
            startActivity(
                Intent(requireContext(), LessonDetailActivity::class.java)
                    .putExtra(LessonDetailActivity.EXTRA_LESSON_ID, next.id)
            )
        }

        binding.btnViewAchievements.setOnClickListener {
            startActivity(Intent(requireContext(), AchievementsActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
