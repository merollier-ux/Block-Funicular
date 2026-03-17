package com.pythonaudio.pytutor.ui.achievements

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pythonaudio.pytutor.R
import com.pythonaudio.pytutor.model.Achievement
import com.pythonaudio.pytutor.databinding.ItemAchievementBinding

class AchievementsAdapter(
    private val achievements: List<Achievement>,
    private val unlockedIds: Set<String>
) : RecyclerView.Adapter<AchievementsAdapter.AchievementViewHolder>() {

    inner class AchievementViewHolder(val binding: ItemAchievementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val binding = ItemAchievementBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AchievementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        val achievement = achievements[position]
        val unlocked = achievement.id in unlockedIds
        val b = holder.binding

        b.tvAchievementTitle.text = achievement.title
        b.tvAchievementDescription.text = achievement.description
        b.tvXpReward.text = "+${achievement.xpReward} XP"
        b.tvRarity.text = achievement.rarity.label
        b.tvRarity.setTextColor(Color.parseColor(achievement.rarity.colorHex))

        if (unlocked) {
            b.tvAchievementIcon.text = achievement.icon
            b.tvStatusIcon.text = "✅"
            b.cardAchievement.alpha = 1f
            b.cardIcon.setCardBackgroundColor(Color.parseColor("#1A0D2E"))
            b.cardAchievement.strokeColor = Color.parseColor(achievement.rarity.colorHex)
            b.tvAchievementTitle.setTextColor(Color.WHITE)
        } else {
            b.tvAchievementIcon.text = "🔒"
            b.tvStatusIcon.text = "🔒"
            b.cardAchievement.alpha = 0.5f
            b.cardIcon.setCardBackgroundColor(Color.parseColor("#1A1A1A"))
            b.cardAchievement.strokeColor = Color.parseColor("#333333")
            b.tvAchievementTitle.setTextColor(Color.parseColor("#888888"))
        }
    }

    override fun getItemCount() = achievements.size
}
