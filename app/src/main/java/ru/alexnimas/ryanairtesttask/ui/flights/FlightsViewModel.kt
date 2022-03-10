package ru.alexnimas.ryanairtesttask.ui.flights

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.alexnimas.ryanairtesttask.SingleLiveEvent
import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight
import ru.alexnimas.ryanairtesttask.domain.request.FlightsRequest
import ru.alexnimas.ryanairtesttask.domain.usecase.FlightsUseCase
import ru.alexnimas.ryanairtesttask.ui.mappers.FlightsMapper
import ru.alexnimas.ryanairtesttask.ui.model.Flight
import javax.inject.Inject

@HiltViewModel
class FlightsViewModel @Inject constructor(
    private val flightsUseCase: FlightsUseCase,
    private val flightsMapper: FlightsMapper
) : ViewModel() {
    private val disposables = CompositeDisposable()

    private var fullFlights = emptyList<DomainFlight>()

    private val _flights = MutableLiveData<List<Flight>>()
    val flights: LiveData<List<Flight>>
        get() = _flights

    private val _showFlightDetails = SingleLiveEvent<DomainFlight>()
    val showFlightDetails: LiveData<DomainFlight>
        get() = _showFlightDetails

    fun loadFlights(flightsRequest: FlightsRequest) {
        disposables.add(
            flightsUseCase(flightsRequest)
                .map { domainFlights ->
                    fullFlights = domainFlights
                    domainFlights.map { flightsMapper.mapToPresentationModel(it) }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ flights ->
                    _flights.postValue(flights)
                }, {
                    Log.d("TAGF", "Here we can handle some errors, for example show toast, etc.")
                })
        )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun onFlightClicked(flight: Flight) {
        val fullFlight = fullFlights.firstOrNull { flight.flightCode == it.flightNumber }
        fullFlight?.let { _showFlightDetails.postValue(it) }
    }
}