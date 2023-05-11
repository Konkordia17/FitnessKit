package com.example.fitnesskit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnesskit.domain.models.RowType
import com.example.fitnesskit.domain.use_cases.GetTrainingScheduleUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrainingScheduleViewModel(private val useCase: GetTrainingScheduleUseCase) : ViewModel() {
    private val _trainingList = MutableLiveData<List<RowType>>()
    val trainingList: LiveData<List<RowType>> = _trainingList

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        getTrainingList()
    }

    private fun getTrainingList() {
        scope.launch(handler) {
            val trainings = useCase.getTrainingSchedules()
            _trainingList.postValue(trainings)
        }
    }

}