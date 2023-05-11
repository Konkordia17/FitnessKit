package com.example.fitnesskit.domain.repository

import com.example.fitnesskit.data.storage.models.Schedule

interface ScheduleRepository {

    suspend fun getSchedule(): Schedule
}