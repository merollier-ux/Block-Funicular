package com.pythonaudio.pytutor.model

data class UserLevel(
    val number: Int,
    val title: String,
    val minXp: Int,
    val maxXp: Int,
    val emoji: String,
    val colorHex: String
)

object LevelSystem {
    val levels = listOf(
        UserLevel(1, "Beginner",       0,    150,         "🥚", "#9E9E9E"),
        UserLevel(2, "Novice",         150,  350,         "🐣", "#4CAF50"),
        UserLevel(3, "Learner",        350,  650,         "🐍", "#2196F3"),
        UserLevel(4, "Coder",          650,  1100,        "💻", "#9C27B0"),
        UserLevel(5, "Developer",      1100, 1700,        "⚙️", "#FF9800"),
        UserLevel(6, "Engineer",       1700, 2500,        "🔧", "#F44336"),
        UserLevel(7, "Expert",         2500, 3500,        "🌟", "#E91E63"),
        UserLevel(8, "Audio ML Master",3500, Int.MAX_VALUE,"🎵", "#FFD700"),
    )

    fun forXp(xp: Int): UserLevel = levels.lastOrNull { xp >= it.minXp } ?: levels.first()

    fun progressFraction(xp: Int): Float {
        val level = forXp(xp)
        if (level.maxXp == Int.MAX_VALUE) return 1f
        return (xp - level.minXp).toFloat() / (level.maxXp - level.minXp)
    }

    fun xpToNext(xp: Int): Int {
        val level = forXp(xp)
        return if (level.maxXp == Int.MAX_VALUE) 0 else level.maxXp - xp
    }
}
