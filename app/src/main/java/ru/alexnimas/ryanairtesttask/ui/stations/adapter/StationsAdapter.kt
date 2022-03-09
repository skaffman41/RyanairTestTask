package ru.alexnimas.ryanairtesttask.ui.stations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.alexnimas.ryanairtesttask.databinding.ItemStationBinding
import ru.alexnimas.ryanairtesttask.domain.model.Station

class StationsAdapter(
    diffCallback: DiffUtil.ItemCallback<Station>,
    private val onItemClick: (station: Station) -> Unit
) : ListAdapter<Station, StationsAdapter.StationHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationHolder {
        val binding = ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return StationHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: StationHolder, position: Int) {
        val station = getItem(position)
        holder.bind(station)
    }

    class StationHolder(
        private val binding: ItemStationBinding,
        private val onItemClick: (station: Station) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(station: Station) {
            binding.stationCity.text = station.name
            binding.stationCountry.text = station.countryName
            binding.stationCode.text = station.code
            binding.root.setOnClickListener { onItemClick.invoke(station) }
        }
    }
}