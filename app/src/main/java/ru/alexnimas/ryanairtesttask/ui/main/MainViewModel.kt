package ru.alexnimas.ryanairtesttask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexnimas.ryanairtesttask.domain.FlightStorage
import ru.alexnimas.ryanairtesttask.domain.model.Passengers
import ru.alexnimas.ryanairtesttask.ui.main.mappers.PassengersMapper
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val passengersMapper: PassengersMapper,
    private val flightStorage: FlightStorage
) : ViewModel() {

    val from: LiveData<String> = Transformations.map(flightStorage.flight) { model ->
        val from = model?.from?.name ?: ""
        from
    }

    val to: LiveData<String> = Transformations.map(flightStorage.flight) { model ->
        model?.to?.name ?: ""
    }

    val flyOut: LiveData<String> = Transformations.map(flightStorage.flight) { model ->
        model?.flyOut ?: ""
    }

    val passengers: LiveData<String> = Transformations.map(flightStorage.flight) { model ->
        passengersMapper.mapPassengers(model?.passengers ?: Passengers())
    }

    fun updateFlyOut(year: Int, month: Int, day: Int) {
        val newFlight = flightStorage.flight.value?.copy(flyOut = "$year-$month-$day")
        flightStorage.updateFlight(newFlight)
    }
}