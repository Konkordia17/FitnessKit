package com.example.fitnesskit.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesskit.databinding.ItemTrainBinding
import com.example.fitnesskit.domain.models.TrainingDescription
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class TrainDescriptionDelegate : AdapterDelegate<List<Any>>() {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is TrainingDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTrainBinding.inflate(inflater, parent, false)
        return TrainViewHolder(binding)
    }


    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as TrainViewHolder).bind(items[position] as TrainingDescription)
    }

    class TrainViewHolder(
        private val binding: ItemTrainBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(training: TrainingDescription) {
            with(binding) {
                startTimeTv.text = training.startTime
                endTimeTv.text = training.endTime
                couchNameTv.text = training.coachName
                placeTv.text = training.place
                trainingTv.text = training.title
            }

        }
    }
}


