package ru.alexnimas.ryanairtesttask.ui.passengers

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexnimas.ryanairtesttask.domain.FlightStorage
import ru.alexnimas.ryanairtesttask.domain.model.Passengers
import javax.inject.Inject

@HiltViewModel
class PassengersViewModel @Inject constructor(
    private val flightStorage: FlightStorage
) : ViewModel() {
    fun applyPassengers(adult: Int, children: Int, teen: Int, infant: Int) {
        flightStorage.updateFlight(
            flightSearchModel = flightStorage.flight.value?.copy(
                passengers = Passengers(
                    adult = adult,
                    children = children,
                    teen = teen,
                    infant = infant
                )
            )
        )
    }
}