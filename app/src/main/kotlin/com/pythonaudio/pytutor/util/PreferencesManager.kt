package com.pythonaudio.pytutor.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // ── API Key ──────────────────────────────────────────────────────────────
    var apiKey: String
        get() = prefs.getString(KEY_API_KEY, "") ?: ""
        set(value) = prefs.edit().putString(KEY_API_KEY, value).apply()

    fun hasApiKey(): Boolean = apiKey.isNotBlank()

    // ── XP & Levels ──────────────────────────────────────────────────────────
    var totalXp: Int
        get() = prefs.getInt(KEY_TOTAL_XP, 0)
        set(value) = prefs.edit().putInt(KEY_TOTAL_XP, value.coerceAtLeast(0)).apply()

    fun addXp(amount: Int) { totalXp += amount }

    // ── Streak ───────────────────────────────────────────────────────────────
    var currentStreak: Int
        get() = prefs.getInt(KEY_STREAK, 0)
        set(value) = prefs.edit().putInt(KEY_STREAK, value.coerceAtLeast(0)).apply()

    var lastStudyDate: Long
        get() = prefs.getLong(KEY_LAST_STUDY, 0L)
        set(value) = prefs.edit().putLong(KEY_LAST_STUDY, value).apply()

    // ── Lives ─────────────────────────────────────────────────────────────────
    var lives: Int
        get() = prefs.getInt(KEY_LIVES, MAX_LIVES)
        set(value) = prefs.edit().putInt(KEY_LIVES, value.coerceIn(0, MAX_LIVES)).apply()

    var lastLivesRegenTime: Long
        get() = prefs.getLong(KEY_LIVES_REGEN, System.currentTimeMillis())
        set(value) = prefs.edit().putLong(KEY_LIVES_REGEN, value).apply()

    // ── Lesson completion ─────────────────────────────────────────────────────
    fun isLessonCompleted(lessonId: String): Boolean =
        prefs.getBoolean("done_$lessonId", false)

    fun markLessonCompleted(lessonId: String, xp: Int) {
        prefs.edit().putBoolean("done_$lessonId", true).apply()
        totalXp += xp
    }

    fun getCompletedLessonCount(): Int =
        prefs.all.count { it.key.startsWith("done_") && it.value == true }

    // ── Per-lesson adaptive tracking ──────────────────────────────────────────
    fun getLessonAttempts(lessonId: String): Int = prefs.getInt("att_$lessonId", 0)
    fun incrementAttempts(lessonId: String) =
        prefs.edit().putInt("att_$lessonId", getLessonAttempts(lessonId) + 1).apply()

    fun getLessonHintsUsed(lessonId: String): Int = prefs.getInt("hint_$lessonId", 0)
    fun incrementHints(lessonId: String) =
        prefs.edit().putInt("hint_$lessonId", getLessonHintsUsed(lessonId) + 1).apply()

    fun wasFirstTry(lessonId: String): Boolean = prefs.getBoolean("first_$lessonId", false)
    fun setFirstTry(lessonId: String) = prefs.edit().putBoolean("first_$lessonId", true).apply()

    fun wasStruggled(lessonId: String): Boolean = prefs.getBoolean("struggle_$lessonId", false)
    fun setStruggled(lessonId: String) = prefs.edit().putBoolean("struggle_$lessonId", true).apply()

    // ── Global stats ──────────────────────────────────────────────────────────
    var totalCodeRuns: Int
        get() = prefs.getInt(KEY_CODE_RUNS, 0)
        set(value) = prefs.edit().putInt(KEY_CODE_RUNS, value).apply()
    fun incrementCodeRuns() { totalCodeRuns++ }

    var totalChatMessages: Int
        get() = prefs.getInt(KEY_CHAT_MSGS, 0)
        set(value) = prefs.edit().putInt(KEY_CHAT_MSGS, value).apply()
    fun incrementChatMessages() { totalChatMessages++ }

    var totalErrorsFixed: Int
        get() = prefs.getInt(KEY_ERRORS_FIXED, 0)
        set(value) = prefs.edit().putInt(KEY_ERRORS_FIXED, value).apply()
    fun incrementErrorsFixed() { totalErrorsFixed++ }

    // ── Achievements ──────────────────────────────────────────────────────────
    fun isAchievementUnlocked(id: String): Boolean = prefs.getBoolean("ach_$id", false)
    fun unlockAchievement(id: String) = prefs.edit().putBoolean("ach_$id", true).apply()
    fun getUnlockedCount(): Int = prefs.all.count { it.key.startsWith("ach_") && it.value == true }

    companion object {
        private const val PREFS_NAME      = "pytutor_prefs"
        private const val KEY_API_KEY     = "claude_api_key"
        private const val KEY_TOTAL_XP    = "total_xp"
        private const val KEY_STREAK      = "current_streak"
        private const val KEY_LAST_STUDY  = "last_study_date"
        private const val KEY_LIVES       = "lives"
        private const val KEY_LIVES_REGEN = "lives_regen_time"
        private const val KEY_CODE_RUNS   = "code_runs"
        private const val KEY_CHAT_MSGS   = "chat_msgs"
        private const val KEY_ERRORS_FIXED= "errors_fixed"

        const val MAX_LIVES = 5
        const val LIVES_REGEN_MINUTES = 30L  // +1 life every 30 min
    }
}
