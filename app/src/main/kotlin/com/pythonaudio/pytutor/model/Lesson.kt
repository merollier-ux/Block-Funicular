package com.pythonaudio.pytutor.model

data class Lesson(
    val id: String,
    val moduleId: Int,
    val title: String,
    val subtitle: String,
    val icon: String,
    val xpReward: Int,
    val estimatedMinutes: Int,
    val theory: String,
    val codeExample: String,
    val exercise: Exercise,
    val tags: List<String> = emptyList()
)

data class Exercise(
    val prompt: String,
    val starterCode: String,
    val solution: String,
    val hints: List<String>
)

data class Module(
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val color: String,
    val lessons: List<Lesson>
)
