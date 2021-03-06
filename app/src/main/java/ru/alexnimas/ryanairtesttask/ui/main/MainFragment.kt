package ru.alexnimas.ryanairtesttask.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.alexnimas.ryanairtesttask.R
import ru.alexnimas.ryanairtesttask.databinding.FragmentMainBinding
import ru.alexnimas.ryanairtesttask.domain.request.FlightsRequest
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModels<MainViewModel>()
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setListeners()
    }

    private fun observeViewModel() {
        viewModel.from.observe(viewLifecycleOwner) { binding.from.text = it }
        viewModel.to.observe(viewLifecycleOwner) { binding.to.text = it }
        viewModel.flyOut.observe(viewLifecycleOwner) { binding.flyOut.text = it }
        viewModel.passengers.observe(viewLifecycleOwner) { binding.passengers.text = it }
        viewModel.showFlights.observe(viewLifecycleOwner) { navigateToFlights(it) }
    }

    private fun navigateToFlights(flightsRequest: FlightsRequest) {
        findNavController().navigate(MainFragmentDirections.mainToFlights(flightsRequest))
    }

    private fun setListeners() {
        binding.from.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.mainToStations(StationType.FROM))
        }

        binding.to.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.mainToStations(StationType.TO))
        }

        binding.flyOut.setOnClickListener {
            showDatePickerDialog()
        }

        binding.passengers.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.mainToPassengers())
        }

        binding.search.setOnClickListener {
            viewModel.goToFlights()
        }
    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            requireContext(),
            { _, y, m, d -> viewModel.updateFlyOut(y, m + 1, d) },
            year,
            month,
            day
        ).show()
    }
}

enum class StationType {
    FROM,
    TO
}