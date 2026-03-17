package com.pythonaudio.pytutor.util

import com.pythonaudio.pytutor.data.LessonsRepository
import com.pythonaudio.pytutor.model.Achievement
import com.pythonaudio.pytutor.model.AchievementsRepository

object AchievementManager {

    /** Check all achievements and return any newly unlocked ones. */
    fun checkAll(prefs: PreferencesManager): List<Achievement> {
        val newlyUnlocked = mutableListOf<Achievement>()
        for (achievement in AchievementsRepository.all) {
            if (prefs.isAchievementUnlocked(achievement.id)) continue
            if (shouldUnlock(achievement.id, prefs)) {
                prefs.unlockAchievement(achievement.id)
                prefs.addXp(achievement.xpReward)
                newlyUnlocked.add(achievement)
            }
        }
        return newlyUnlocked
    }

    private fun shouldUnlock(id: String, prefs: PreferencesManager): Boolean {
        val allLessons = LessonsRepository.getAllLessons()
        val modules    = LessonsRepository.getModules()
        return when (id) {
            "first_lesson"  -> prefs.getCompletedLessonCount() >= 1
            "hello_world"   -> prefs.totalCodeRuns >= 1
            "quick_learner" -> allLessons.any { prefs.wasFirstTry(it.id) }
            "hint_free"     -> allLessons.any {
                prefs.isLessonCompleted(it.id) && prefs.getLessonHintsUsed(it.id) == 0
            }
            "bug_hunter"    -> prefs.totalErrorsFixed >= 1
            "comeback_kid"  -> allLessons.any {
                prefs.isLessonCompleted(it.id) && prefs.wasStruggled(it.id)
            }
            "streak_3"      -> prefs.currentStreak >= 3
            "streak_7"      -> prefs.currentStreak >= 7
            "module_1_done" -> isModuleComplete(1, prefs, modules)
            "module_5_done" -> isModuleComplete(5, prefs, modules)
            "module_6_done" -> isModuleComplete(6, prefs, modules)
            "module_7_done" -> isModuleComplete(7, prefs, modules)
            "module_8_done" -> isModuleComplete(8, prefs, modules)
            "module_9_done" -> isModuleComplete(9, prefs, modules)
            "tutor_fan"     -> prefs.totalChatMessages >= 20
            "code_machine"  -> prefs.totalCodeRuns >= 25
            "all_done"      -> prefs.getCompletedLessonCount() >= allLessons.size
            else            -> false
        }
    }

    private fun isModuleComplete(
        moduleId: Int,
        prefs: PreferencesManager,
        modules: List<com.pythonaudio.pytutor.model.Module>
    ): Boolean {
        val module = modules.find { it.id == moduleId } ?: return false
        return module.lessons.all { prefs.isLessonCompleted(it.id) }
    }
}
