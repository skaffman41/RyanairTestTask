package ru.alexnimas.ryanairtesttask.ui.flights.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.alexnimas.ryanairtesttask.ui.model.Flight

class FlightDiffUtilCallback: DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.flightCode == newItem.flightCode
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }
}