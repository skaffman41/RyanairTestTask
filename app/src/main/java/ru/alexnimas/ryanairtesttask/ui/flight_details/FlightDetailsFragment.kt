package ru.alexnimas.ryanairtesttask.ui.flight_details

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.alexnimas.ryanairtesttask.R
import ru.alexnimas.ryanairtesttask.databinding.FragmentFlightDetailsBinding
import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight

@AndroidEntryPoint
class FlightDetailsFragment : Fragment(R.layout.fragment_flight_details) {
    private val args: FlightDetailsFragmentArgs by navArgs()
    private val binding: FragmentFlightDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(args.flight)
    }

    private fun setData(flight: DomainFlight) {
        binding.flightDetails.text = """
            ${flight.time}
            ${flight.duration}
            ${flight.faresLeft}
            ${flight.flightKey}
            ${flight.flightNumber}
            ${flight.infantsLeft}
            ${flight.operatedBy}
            ${flight.regularFare}
            ${flight.segments}
            ${flight.timeUTC}
        """.trimIndent()
    }
}
