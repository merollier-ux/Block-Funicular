package com.pythonaudio.pytutor.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var apiKey: String
        get() = prefs.getString(KEY_API_KEY, "") ?: ""
        set(value) = prefs.edit().putString(KEY_API_KEY, value).apply()

    var totalXp: Int
        get() = prefs.getInt(KEY_TOTAL_XP, 0)
        set(value) = prefs.edit().putInt(KEY_TOTAL_XP, value).apply()

    var currentStreak: Int
        get() = prefs.getInt(KEY_STREAK, 0)
        set(value) = prefs.edit().putInt(KEY_STREAK, value).apply()

    var lastStudyDate: Long
        get() = prefs.getLong(KEY_LAST_STUDY, 0L)
        set(value) = prefs.edit().putLong(KEY_LAST_STUDY, value).apply()

    fun isLessonCompleted(lessonId: String): Boolean =
        prefs.getBoolean("lesson_$lessonId", false)

    fun markLessonCompleted(lessonId: String, xp: Int) {
        prefs.edit().putBoolean("lesson_$lessonId", true).apply()
        totalXp += xp
    }

    fun getCompletedLessonCount(): Int {
        return prefs.all.keys.count { it.startsWith("lesson_") && prefs.getBoolean(it, false) }
    }

    fun hasApiKey(): Boolean = apiKey.isNotBlank()

    companion object {
        private const val PREFS_NAME = "pytutor_prefs"
        private const val KEY_API_KEY = "claude_api_key"
        private const val KEY_TOTAL_XP = "total_xp"
        private const val KEY_STREAK = "current_streak"
        private const val KEY_LAST_STUDY = "last_study_date"
    }
}
