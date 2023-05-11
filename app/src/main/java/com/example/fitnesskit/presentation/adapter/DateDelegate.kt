package com.example.fitnesskit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesskit.databinding.ItemDateBinding
import com.example.fitnesskit.domain.models.RowType
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class DateDelegate : AdapterDelegate<List<Any>>() {

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is RowType.TrainingData
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDateBinding.inflate(inflater, parent, false)
        return DateViewHolder(binding)
    }


    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as DateViewHolder).bind(items[position] as RowType.TrainingData)
    }

    class DateViewHolder(
        private val binding: ItemDateBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: RowType.TrainingData) {
            binding.dateTv.text = date.date
        }
    }
}