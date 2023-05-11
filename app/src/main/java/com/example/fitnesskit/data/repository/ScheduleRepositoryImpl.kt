package com.example.fitnesskit.data.repository

import com.example.fitnesskit.data.storage.TrainsApi
import com.example.fitnesskit.data.storage.models.Schedule
import com.example.fitnesskit.domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(private val api: TrainsApi) : ScheduleRepository {

    override suspend fun getSchedule(): Schedule {
        return api.getSchedule()
    }
}