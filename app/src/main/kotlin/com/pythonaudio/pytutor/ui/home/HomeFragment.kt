package com.pythonaudio.pytutor.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pythonaudio.pytutor.R
import com.pythonaudio.pytutor.data.LessonsRepository
import com.pythonaudio.pytutor.databinding.FragmentHomeBinding
import com.pythonaudio.pytutor.ui.lessons.LessonDetailActivity
import com.pythonaudio.pytutor.util.PreferencesManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var prefsManager: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefsManager = PreferencesManager(requireContext())
        updateUI()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        val completedCount = prefsManager.getCompletedLessonCount()
        val totalLessons = LessonsRepository.getAllLessons().size
        val xp = prefsManager.totalXp
        val streak = prefsManager.currentStreak

        // Header greeting
        binding.tvGreeting.text = "Welcome back, coder! 👋"
        binding.tvSubtitle.text = "Your Python + Audio AI journey continues"

        // Stats
        binding.tvXpValue.text = "$xp XP"
        binding.tvStreakValue.text = "$streak days"
        binding.tvLessonsValue.text = "$completedCount / $totalLessons"

        // Progress bar
        val progress = if (totalLessons > 0) (completedCount * 100) / totalLessons else 0
        binding.progressOverall.progress = progress
        binding.tvProgressPercent.text = "$progress% complete"

        // API key warning
        if (!prefsManager.hasApiKey()) {
            binding.cardApiKeyWarning.visibility = View.VISIBLE
        } else {
            binding.cardApiKeyWarning.visibility = View.GONE
        }

        // Next lesson to study
        val nextLesson = LessonsRepository.getAllLessons()
            .firstOrNull { !prefsManager.isLessonCompleted(it.id) }

        if (nextLesson != null) {
            binding.cardNextLesson.visibility = View.VISIBLE
            binding.tvNextLessonTitle.text = nextLesson.title
            binding.tvNextLessonSubtitle.text = nextLesson.subtitle
            binding.tvNextLessonXp.text = "+${nextLesson.xpReward} XP"
            binding.tvNextLessonTime.text = "~${nextLesson.estimatedMinutes} min"
            binding.btnStartLesson.setOnClickListener {
                val intent = Intent(requireContext(), LessonDetailActivity::class.java)
                intent.putExtra(LessonDetailActivity.EXTRA_LESSON_ID, nextLesson.id)
                startActivity(intent)
            }
        } else {
            binding.cardNextLesson.visibility = View.GONE
            binding.tvAllDone.visibility = View.VISIBLE
        }

        // Featured modules
        updateModuleCards()
    }

    private fun updateModuleCards() {
        val modules = LessonsRepository.modules
        binding.tvModule1Title.text = modules[0].icon + " " + modules[0].title
        binding.tvModule2Title.text = modules[1].icon + " " + modules[1].title
        binding.tvModule3Title.text = modules[2].icon + " " + modules[2].title
        binding.tvModule4Title.text = if (modules.size > 3) modules[3].icon + " " + modules[3].title else "📋 Lists & Data"
    }

    private fun setupClickListeners() {
        binding.btnSetupApiKey.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_settings)
        }

        binding.btnGoToLessons.setOnClickListener {
            findNavController().navigate(R.id.navigation_lessons)
        }

        binding.btnGoToCodeLab.setOnClickListener {
            findNavController().navigate(R.id.navigation_code_lab)
        }

        binding.btnGoToTutor.setOnClickListener {
            findNavController().navigate(R.id.navigation_tutor)
        }

        binding.cardModule1.setOnClickListener {
            findNavController().navigate(R.id.navigation_lessons)
        }
        binding.cardModule2.setOnClickListener {
            findNavController().navigate(R.id.navigation_lessons)
        }
        binding.cardModule3.setOnClickListener {
            findNavController().navigate(R.id.navigation_lessons)
        }
        binding.cardModule4.setOnClickListener {
            findNavController().navigate(R.id.navigation_lessons)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
