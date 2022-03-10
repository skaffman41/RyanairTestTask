package ru.alexnimas.ryanairtesttask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexnimas.ryanairtesttask.SingleLiveEvent
import ru.alexnimas.ryanairtesttask.domain.FlightStorage
import ru.alexnimas.ryanairtesttask.domain.model.Passengers
import ru.alexnimas.ryanairtesttask.domain.request.FlightsRequest
import ru.alexnimas.ryanairtesttask.ui.mappers.PassengersMapper
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val passengersMapper: PassengersMapper,
    private val flightStorage: FlightStorage
) : ViewModel() {

    private val _showErrorEmptyFields = SingleLiveEvent<Void>()
    val showErrorEmptyFields: LiveData<Void>
        get() = _showErrorEmptyFields

    private val _showFlights = SingleLiveEvent<FlightsRequest>()
    val showFlights: LiveData<FlightsRequest>
        get() = _showFlights

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

    fun goToFlights() {
        val flight = flightStorage.flight.value
        val dateout = flight?.flyOut
        val origin = flight?.from?.code
        val destination = flight?.to?.code
        val adult = flight?.passengers?.adult ?: 0
        val children = flight?.passengers?.children ?: 0
        val teen = flight?.passengers?.teen ?: 0
        val infant = flight?.passengers?.infant ?: 0

        if (dateout == null || origin == null || destination == null) {
            _showErrorEmptyFields.call()
        } else {
            val flightsRequest = FlightsRequest(
                dateout = dateout,
                origin = origin,
                destination = destination,
                adt = adult,
                chd = children,
                teen = teen,
                inf = infant
            )
            _showFlights.postValue(flightsRequest)
        }
    }
}