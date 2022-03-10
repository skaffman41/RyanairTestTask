package ru.alexnimas.ryanairtesttask.ui.stations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.alexnimas.ryanairtesttask.databinding.ItemCountryBinding
import ru.alexnimas.ryanairtesttask.databinding.ItemStationBinding
import ru.alexnimas.ryanairtesttask.ui.model.Country
import ru.alexnimas.ryanairtesttask.ui.model.IStation
import ru.alexnimas.ryanairtesttask.ui.model.Station

class StationsAdapter(
    diffCallback: DiffUtil.ItemCallback<IStation>,
    private val onItemClick: (station: Station) -> Unit
) : ListAdapter<IStation, RecyclerView.ViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item is Station) 1 else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val stationBinding =
            ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val countryBinding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return if (viewType == 1) {
            StationHolder(stationBinding, onItemClick)
        } else {
            CountryHolder(countryBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item is Station) {
            (holder as StationHolder).bind(item)
        } else if (item is Country) {
            (holder as CountryHolder).bind(item)
        }
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

    class CountryHolder(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.country.text = country.name
        }
    }
}