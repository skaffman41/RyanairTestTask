package ru.alexnimas.ryanairtesttask.ui.stations

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.alexnimas.ryanairtesttask.R
import ru.alexnimas.ryanairtesttask.databinding.FragmentStationsBinding
import ru.alexnimas.ryanairtesttask.ui.model.Station
import ru.alexnimas.ryanairtesttask.ui.stations.adapter.StationDiffUtilCallback
import ru.alexnimas.ryanairtesttask.ui.stations.adapter.StationsAdapter
import ru.alexnimas.ryanairtesttask.utils.autoCleaned

@AndroidEntryPoint
class StationsFragment : Fragment(R.layout.fragment_stations) {
    private val args: StationsFragmentArgs by navArgs()
    private val binding: FragmentStationsBinding by viewBinding()
    private val viewModel: StationsViewModel by viewModels()
    private val adapter by autoCleaned {
        StationsAdapter(
            diffCallback = StationDiffUtilCallback(),
            onItemClick = { station -> onStationClick(station) }
        )
    }

    private fun onStationClick(station: Station) {
        viewModel.updateFlight(args.stationType, station)
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.stations.adapter = adapter
        observeViewModel()
        setListeners()
    }

    private fun setListeners() {
        binding.search.doOnTextChanged { text, _, _, _ ->
            viewModel.searchSubject.onNext(text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.stations.observe(viewLifecycleOwner) { stations ->
            adapter.submitList(stations)
        }
    }
}