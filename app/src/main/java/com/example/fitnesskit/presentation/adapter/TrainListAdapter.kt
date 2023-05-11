package com.example.fitnesskit.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesskit.domain.models.RowType
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class TrainListAdapter : ListAdapter<RowType, RecyclerView.ViewHolder>(DiffUtilCallback()) {

    private val delegatesManager = AdapterDelegatesManager<List<Any>>()

    init {
        delegatesManager.addDelegate(DateDelegate())
        delegatesManager.addDelegate(TrainDescriptionDelegate())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(currentList, position, holder)
    }


    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(currentList, position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<RowType>() {

    override fun areItemsTheSame(oldItem: RowType, newItem: RowType): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RowType, newItem: RowType): Boolean {
        return oldItem == newItem
    }
}
