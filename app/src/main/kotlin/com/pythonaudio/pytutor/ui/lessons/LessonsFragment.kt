package com.pythonaudio.pytutor.ui.lessons

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pythonaudio.pytutor.data.LessonsRepository
import com.pythonaudio.pytutor.databinding.FragmentLessonsBinding
import com.pythonaudio.pytutor.util.PreferencesManager

class LessonsFragment : Fragment() {

    private var _binding: FragmentLessonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val prefs = PreferencesManager(requireContext())
        val modules = LessonsRepository.modules

        val adapter = ModulesAdapter(modules, prefs) { lesson ->
            val intent = Intent(requireContext(), LessonDetailActivity::class.java)
            intent.putExtra(LessonDetailActivity.EXTRA_LESSON_ID, lesson.id)
            startActivity(intent)
        }

        binding.recyclerModules.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerModules.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
