package ru.alexnimas.ryanairtesttask.ui.flights.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.alexnimas.ryanairtesttask.databinding.ItemFlightBinding
import ru.alexnimas.ryanairtesttask.ui.model.Flight

class FlightsAdapter(
    diffCallback: DiffUtil.ItemCallback<Flight>,
    private val onItemClick: (flight: Flight) -> Unit
) : ListAdapter<Flight, FlightsAdapter.FlightHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val binding = ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FlightHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        val station = getItem(position)
        holder.bind(station)
    }

    class FlightHolder(
        private val binding: ItemFlightBinding,
        private val onItemClick: (flight: Flight) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flight: Flight) {
            binding.departureTime.text = flight.timeFrom
            binding.departureCode.text = flight.codeFrom
            binding.flightTime.text = flight.duration
            binding.flightCode.text = flight.flightCode
            binding.arrivalTime.text = flight.timeTo
            binding.arrivalCode.text = flight.codeTo
            binding.price.text = flight.price
            binding.root.setOnClickListener { onItemClick.invoke(flight) }
        }
    }
}