package ru.alexnimas.ryanairtesttask.ui.stations.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.alexnimas.ryanairtesttask.domain.model.Station

class StationDiffUtilCallback: DiffUtil.ItemCallback<Station>() {
    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem == newItem
    }
}