package com.pythonaudio.pytutor.ui.lessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.pythonaudio.pytutor.R
import com.pythonaudio.pytutor.model.Lesson
import com.pythonaudio.pytutor.model.Module
import com.pythonaudio.pytutor.util.PreferencesManager

class ModulesAdapter(
    private val modules: List<Module>,
    private val prefs: PreferencesManager,
    private val onLessonClick: (Lesson) -> Unit
) : RecyclerView.Adapter<ModulesAdapter.ModuleViewHolder>() {

    inner class ModuleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardModule: MaterialCardView = view.findViewById(R.id.card_module)
        val tvModuleTitle: TextView = view.findViewById(R.id.tv_module_title)
        val tvModuleDescription: TextView = view.findViewById(R.id.tv_module_description)
        val tvModuleProgress: TextView = view.findViewById(R.id.tv_module_progress)
        val recyclerLessons: RecyclerView = view.findViewById(R.id.recycler_lessons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_module, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = modules[position]
        val completedInModule = module.lessons.count { prefs.isLessonCompleted(it.id) }

        holder.tvModuleTitle.text = "${module.icon} ${module.title}"
        holder.tvModuleDescription.text = module.description
        holder.tvModuleProgress.text = "$completedInModule / ${module.lessons.size} lessons"

        val lessonsAdapter = LessonsAdapter(module.lessons, prefs, onLessonClick)
        holder.recyclerLessons.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.recyclerLessons.adapter = lessonsAdapter
        holder.recyclerLessons.isNestedScrollingEnabled = false
    }

    override fun getItemCount() = modules.size
}
