package com.example.fitnesskit.data.storage.models

data class Schedule(
    val trainers: List<Trainer>,
    val lessons: List<Lesson>
)

data class Lesson(
    val name: String,
    val place: String,
    val coach_id: String,
    val startTime: String,
    val endTime: String,
    val date: String
)

data class Trainer(
    val id: String,
    val full_name: String
)
