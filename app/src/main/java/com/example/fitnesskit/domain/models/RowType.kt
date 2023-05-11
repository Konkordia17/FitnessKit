package com.example.fitnesskit.domain.models

sealed class RowType {
    data class TrainingData(val date: String) : RowType()
}
