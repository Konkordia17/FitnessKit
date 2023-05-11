package com.example.fitnesskit.di

import com.example.fitnesskit.presentation.TrainingScheduleFragment
import com.example.fitnesskit.presentation.TrainingScheduleViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    fun injectTrainingScheduleFragment(trainFragment: TrainingScheduleFragment)

    fun getViewModelFactory(): TrainingScheduleViewModelFactory
}