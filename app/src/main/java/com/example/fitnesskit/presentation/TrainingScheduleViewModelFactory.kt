package com.example.fitnesskit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitnesskit.domain.use_cases.GetTrainingScheduleUseCase
import javax.inject.Inject

class TrainingScheduleViewModelFactory @Inject constructor(
    private val getTrainingScheduleUseCase: GetTrainingScheduleUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            TrainingScheduleViewModel::class.java -> TrainingScheduleViewModel(
                getTrainingScheduleUseCase
            )

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}