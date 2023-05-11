package com.example.fitnesskit.domain.use_cases

import com.example.fitnesskit.data.storage.models.Lesson
import com.example.fitnesskit.data.storage.models.Schedule
import com.example.fitnesskit.domain.models.RowType
import com.example.fitnesskit.domain.models.TrainingDescription
import com.example.fitnesskit.domain.repository.ScheduleRepository
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GetTrainingScheduleUseCase @Inject constructor(private val repository: ScheduleRepository) {

    suspend fun getTrainingSchedules(): List<RowType> {
        val schedule = repository.getSchedule()
        val trainings = schedule.lessons
            .map { lesson ->
                mapLessonToTrainDescription(lesson, getTrainerName(schedule, lesson))
            }
        return getRowTypeListItems(sortTrainingByData(trainings))
    }

    private fun getTrainerName(schedule: Schedule, lesson: Lesson): String {
        return schedule.trainers.find {
            it.id == lesson.coach_id
        }?.full_name ?: ""
    }

    private fun mapLessonToTrainDescription(
        lesson: Lesson,
        coachName: String
    ): TrainingDescription {
        return TrainingDescription(
            title = lesson.name,
            place = lesson.place,
            coachName = coachName,
            startTime = lesson.startTime,
            endTime = lesson.endTime,
            date = lesson.date
        )
    }


    private fun formatDate(dateString: String): String {
        val outputFormat = SimpleDateFormat(OUTPUT_FORMAT, Locale(LANGUAGE, COUNTRY))
        val inputFormat = SimpleDateFormat(INPUT_FORMAT, Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date!!)
    }

    private fun sortTrainingByData(trainings: List<TrainingDescription>): List<TrainingDescription> {
        return trainings.sortedWith(comparator)
    }

    private fun getRowTypeListItems(trainings: List<TrainingDescription>): List<RowType> {
        var currentDate = trainings.first().date
        val schedules: MutableList<RowType> =
            mutableListOf(RowType.TrainingData(date = formatDate(currentDate)))
        trainings.forEach {
            if (it.date == currentDate) {
                schedules.add(it)
            } else {
                currentDate = it.date
                schedules.add(RowType.TrainingData(date = formatDate(currentDate)))
                schedules.add(it)
            }
        }
        return schedules
    }

    private companion object {
        private const val OUTPUT_FORMAT = "EEEE, dd MMMM"
        private const val INPUT_FORMAT = "yyyy-MM-dd"
        private const val LANGUAGE = "ru"
        private const val COUNTRY = "RU"

        private val comparator = Comparator<TrainingDescription> { t1, t2 ->
            if (t1.date != t2.date) {
                t1.date.compareTo(t2.date)
            } else {
                t1.startTime.compareTo(t2.startTime)
            }
        }
    }
}