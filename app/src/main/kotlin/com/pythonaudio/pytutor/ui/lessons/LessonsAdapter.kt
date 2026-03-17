package com.pythonaudio.pytutor.ui.lessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.pythonaudio.pytutor.R
import com.pythonaudio.pytutor.model.Lesson
import com.pythonaudio.pytutor.util.PreferencesManager

class LessonsAdapter(
    private val lessons: List<Lesson>,
    private val prefs: PreferencesManager,
    private val onLessonClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardLesson: MaterialCardView = view.findViewById(R.id.card_lesson)
        val tvIcon: TextView = view.findViewById(R.id.tv_lesson_icon)
        val tvTitle: TextView = view.findViewById(R.id.tv_lesson_title)
        val tvSubtitle: TextView = view.findViewById(R.id.tv_lesson_subtitle)
        val tvXp: TextView = view.findViewById(R.id.tv_lesson_xp)
        val tvTime: TextView = view.findViewById(R.id.tv_lesson_time)
        val tvCompleted: TextView = view.findViewById(R.id.tv_lesson_completed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        val isCompleted = prefs.isLessonCompleted(lesson.id)

        holder.tvIcon.text = lesson.icon
        holder.tvTitle.text = lesson.title
        holder.tvSubtitle.text = lesson.subtitle
        holder.tvXp.text = "+${lesson.xpReward} XP"
        holder.tvTime.text = "~${lesson.estimatedMinutes} min"

        if (isCompleted) {
            holder.tvCompleted.visibility = View.VISIBLE
            holder.tvCompleted.text = "✅"
            holder.cardLesson.alpha = 0.8f
        } else {
            holder.tvCompleted.visibility = View.GONE
            holder.cardLesson.alpha = 1.0f
        }

        holder.cardLesson.setOnClickListener {
            onLessonClick(lesson)
        }
    }

    override fun getItemCount() = lessons.size
}
