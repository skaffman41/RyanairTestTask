package ru.alexnimas.ryanairtesttask.ui.stations.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.alexnimas.ryanairtesttask.ui.model.Country
import ru.alexnimas.ryanairtesttask.ui.model.IStation
import ru.alexnimas.ryanairtesttask.ui.model.Station

class StationDiffUtilCallback : DiffUtil.ItemCallback<IStation>() {
    override fun areItemsTheSame(oldItem: IStation, newItem: IStation): Boolean {
        return when {
            oldItem is Station && newItem is Station -> oldItem.code == newItem.code
            oldItem is Country && newItem is Country -> oldItem.name == newItem.name
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: IStation, newItem: IStation): Boolean {
        return when {
            oldItem is Station && newItem is Station -> oldItem as Station == newItem as Station
            oldItem is Country && newItem is Country -> oldItem as Country == newItem as Country
            else -> false
        }
    }
}