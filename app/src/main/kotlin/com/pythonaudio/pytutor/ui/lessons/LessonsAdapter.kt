package com.pythonaudio.pytutor.ui.lessons

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val root: View           = view.findViewById(R.id.root_lesson)
        val nodeCard: MaterialCardView = view.findViewById(R.id.node_card)
        val tvIcon: TextView     = view.findViewById(R.id.tv_lesson_icon)
        val tvTitle: TextView    = view.findViewById(R.id.tv_lesson_title)
        val tvSubtitle: TextView = view.findViewById(R.id.tv_lesson_subtitle)
        val tvXp: TextView       = view.findViewById(R.id.tv_lesson_xp)
        val tvTime: TextView     = view.findViewById(R.id.tv_lesson_time)
        val tvBadge: TextView    = view.findViewById(R.id.tv_status_badge)
        val tvAction: TextView   = view.findViewById(R.id.tv_action_indicator)
        val connectorTop: View   = view.findViewById(R.id.connector_top)
        val connectorBottom: View = view.findViewById(R.id.connector_bottom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson      = lessons[position]
        val isFirst     = position == 0
        val isLast      = position == lessons.size - 1
        val isCompleted = prefs.isLessonCompleted(lesson.id)
        val prevCompleted = position == 0 || prefs.isLessonCompleted(lessons[position - 1].id)
        val isLocked    = !isCompleted && !prevCompleted

        holder.tvIcon.text     = lesson.icon
        holder.tvTitle.text    = lesson.title
        holder.tvSubtitle.text = lesson.subtitle
        holder.tvXp.text       = "+${lesson.xpReward} XP"
        holder.tvTime.text     = "~${lesson.estimatedMinutes} min"

        // Hide connectors at ends
        holder.connectorTop.visibility    = if (isFirst) View.INVISIBLE else View.VISIBLE
        holder.connectorBottom.visibility = if (isLast)  View.INVISIBLE else View.VISIBLE

        when {
            isCompleted -> {
                // Gold node — completed
                holder.nodeCard.setCardBackgroundColor(Color.parseColor("#2A1E00"))
                holder.nodeCard.strokeColor = Color.parseColor("#FFD700")
                holder.tvIcon.text = "✅"
                holder.tvBadge.visibility = View.GONE
                holder.tvTitle.alpha = 1f
                holder.tvAction.text = "›"
                holder.tvAction.setTextColor(Color.parseColor("#FFD700"))
                holder.root.alpha = 1f
            }
            isLocked -> {
                // Grayed-out — locked
                holder.nodeCard.setCardBackgroundColor(Color.parseColor("#1A1A1A"))
                holder.nodeCard.strokeColor = Color.parseColor("#444444")
                holder.tvIcon.text = "🔒"
                holder.tvBadge.visibility = View.GONE
                holder.tvTitle.alpha = 0.45f
                holder.tvSubtitle.alpha = 0.45f
                holder.tvAction.text = "🔒"
                holder.tvAction.setTextColor(Color.parseColor("#666666"))
                holder.root.alpha = 0.6f
            }
            else -> {
                // Available — primary color
                holder.nodeCard.setCardBackgroundColor(Color.parseColor("#1A0D2E"))
                holder.nodeCard.strokeColor = Color.parseColor("#6200EE")
                holder.tvIcon.text = lesson.icon
                holder.tvBadge.visibility = View.GONE
                holder.tvTitle.alpha = 1f
                holder.tvSubtitle.alpha = 1f
                holder.tvAction.text = "▶"
                holder.tvAction.setTextColor(Color.parseColor("#6200EE"))
                holder.root.alpha = 1f
            }
        }

        // Show "struggling" indicator
        if (!isLocked && prefs.wasStruggled(lesson.id) && !isCompleted) {
            holder.nodeCard.strokeColor = Color.parseColor("#F44336")
        }

        holder.root.setOnClickListener {
            if (!isLocked) onLessonClick(lesson)
        }
    }

    override fun getItemCount() = lessons.size
}
