package com.pythonaudio.pytutor.model

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val xpReward: Int,
    val rarity: Rarity = Rarity.COMMON
) {
    enum class Rarity(val label: String, val colorHex: String) {
        COMMON("Common", "#9E9E9E"),
        RARE("Rare", "#2196F3"),
        EPIC("Epic", "#9C27B0"),
        LEGENDARY("Legendary", "#FFD700")
    }
}

object AchievementsRepository {
    val all: List<Achievement> = listOf(
        Achievement("first_lesson",   "First Steps",       "Complete your first lesson",                  "🐣", 50),
        Achievement("hello_world",    "Hello, World!",     "Run code in Code Lab for the first time",     "💻", 25),
        Achievement("quick_learner",  "Quick Learner",     "Complete an exercise on the very first try",  "⚡", 100, Achievement.Rarity.RARE),
        Achievement("hint_free",      "Flying Solo",       "Complete an exercise without using any hints","🧠", 75,  Achievement.Rarity.RARE),
        Achievement("bug_hunter",     "Bug Hunter",        "Fix your first code error",                   "🐛", 50),
        Achievement("comeback_kid",   "Comeback Kid",      "Complete a lesson after struggling with it",  "🏋️", 100, Achievement.Rarity.RARE),
        Achievement("streak_3",       "On a Roll",         "Maintain a 3-day learning streak",            "🔥", 75),
        Achievement("streak_7",       "Unstoppable",       "7-day streak — you're on fire!",              "💪", 150, Achievement.Rarity.EPIC),
        Achievement("module_1_done",  "Python Born",       "Complete all Python Fundamentals lessons",    "🐍", 200, Achievement.Rarity.RARE),
        Achievement("module_5_done",  "Array Wizard",      "Complete all NumPy lessons",                  "🔢", 200, Achievement.Rarity.RARE),
        Achievement("module_6_done",  "Audio Explorer",    "Complete the Librosa module",                 "🎵", 250, Achievement.Rarity.EPIC),
        Achievement("module_7_done",  "ML Pioneer",        "Complete the Audio ML module",                "🤖", 300, Achievement.Rarity.EPIC),
        Achievement("module_8_done",  "Sound Sculptor",    "Complete all Sound Design lessons",            "🎛️", 250, Achievement.Rarity.EPIC),
        Achievement("module_9_done",  "App Builder",       "Complete all Android Development lessons",    "📱", 250, Achievement.Rarity.EPIC),
        Achievement("tutor_fan",      "Always Learning",   "Send 20 messages to the AI Tutor",            "🤝", 50),
        Achievement("code_machine",   "Code Machine",      "Run code 25 times in Code Lab",               "⚗️", 75),
        Achievement("all_done",       "PyTutor Graduate",  "Complete every single lesson",                "🎓", 500, Achievement.Rarity.LEGENDARY),
    )

    fun getById(id: String) = all.find { it.id == id }
}
