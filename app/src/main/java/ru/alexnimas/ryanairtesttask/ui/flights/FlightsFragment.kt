package ru.alexnimas.ryanairtesttask.ui.flights

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.alexnimas.ryanairtesttask.R
import ru.alexnimas.ryanairtesttask.databinding.FragmentFlightsBinding
import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight
import ru.alexnimas.ryanairtesttask.ui.flights.adapter.FlightDiffUtilCallback
import ru.alexnimas.ryanairtesttask.ui.flights.adapter.FlightsAdapter
import ru.alexnimas.ryanairtesttask.ui.model.Flight
import ru.alexnimas.ryanairtesttask.utils.autoCleaned

@AndroidEntryPoint
class FlightsFragment : Fragment(R.layout.fragment_flights) {
    private val args: FlightsFragmentArgs by navArgs()
    private val binding: FragmentFlightsBinding by viewBinding()
    private val viewModel: FlightsViewModel by viewModels()
    private val adapter by autoCleaned {
        FlightsAdapter(
            diffCallback = FlightDiffUtilCallback(),
            onItemClick = { flight -> onFlightClick(flight) }
        )
    }

    private fun onFlightClick(flight: Flight) {
        viewModel.onFlightClicked(flight)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadFlights(args.flightRequest)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.flights.apply {
            setHasFixedSize(true)
            adapter = this@FlightsFragment.adapter
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.flights.observe(viewLifecycleOwner) { flights -> adapter.submitList(flights) }
        viewModel.showFlightDetails.observe(viewLifecycleOwner) { flight -> showDetails(flight) }
    }

    private fun showDetails(flight: DomainFlight) {
        findNavController().navigate(FlightsFragmentDirections.flightsToDetails(flight))
    }
}