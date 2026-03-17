package com.pythonaudio.pytutor.util

import java.util.concurrent.TimeUnit

object LivesManager {

    /** Regenerate lives based on elapsed time since last regen. Call on app resume. */
    fun regenIfNeeded(prefs: PreferencesManager) {
        if (prefs.lives >= PreferencesManager.MAX_LIVES) {
            prefs.lastLivesRegenTime = System.currentTimeMillis()
            return
        }
        val elapsed = TimeUnit.MILLISECONDS.toMinutes(
            System.currentTimeMillis() - prefs.lastLivesRegenTime
        )
        val toAdd = (elapsed / PreferencesManager.LIVES_REGEN_MINUTES).toInt()
        if (toAdd > 0) {
            prefs.lives = (prefs.lives + toAdd).coerceAtMost(PreferencesManager.MAX_LIVES)
            prefs.lastLivesRegenTime = System.currentTimeMillis()
        }
    }

    /** Spend a life. Returns true if successful, false if out of lives. */
    fun useLife(prefs: PreferencesManager): Boolean {
        if (prefs.lives <= 0) return false
        prefs.lives--
        if (prefs.lives < PreferencesManager.MAX_LIVES) {
            prefs.lastLivesRegenTime = System.currentTimeMillis()
        }
        return true
    }

    /** Minutes until the next life regenerates. 0 if already full. */
    fun minutesUntilNext(prefs: PreferencesManager): Long {
        if (prefs.lives >= PreferencesManager.MAX_LIVES) return 0
        val elapsed = TimeUnit.MILLISECONDS.toMinutes(
            System.currentTimeMillis() - prefs.lastLivesRegenTime
        )
        return (PreferencesManager.LIVES_REGEN_MINUTES - elapsed % PreferencesManager.LIVES_REGEN_MINUTES)
            .coerceAtLeast(0)
    }
}
