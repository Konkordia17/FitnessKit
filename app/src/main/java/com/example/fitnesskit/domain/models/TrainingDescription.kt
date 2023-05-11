package com.example.fitnesskit.domain.models

data class TrainingDescription(
    val title: String,
    val place: String,
    val coachName: String,
    val startTime: String,
    val endTime: String,
    val date: String
) : RowType()
