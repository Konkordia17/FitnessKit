package com.example.fitnesskit.data.storage

import com.example.fitnesskit.data.storage.models.Schedule
import retrofit2.http.GET
import retrofit2.http.Query

interface TrainsApi {

    @GET("/schedule/get_v3/")
    suspend fun getSchedule(@Query("club_id") club_id: Int = 2): Schedule
}