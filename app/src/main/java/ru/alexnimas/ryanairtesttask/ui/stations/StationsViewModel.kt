package ru.alexnimas.ryanairtesttask.ui.stations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.alexnimas.ryanairtesttask.domain.FlightStorage
import ru.alexnimas.ryanairtesttask.domain.usecase.StationsUseCase
import ru.alexnimas.ryanairtesttask.domain.model.Station
import ru.alexnimas.ryanairtesttask.ui.main.StationType
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val stationsUseCase: StationsUseCase,
    private val flightStorage: FlightStorage
) : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _stations = MutableLiveData<List<Station>>()
    val stations: LiveData<List<Station>>
        get() = _stations

    fun loadStations() {
        disposables.add(
            stationsUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ stations ->
                    _stations.postValue(stations)
                }, {

                })
        )
    }

    fun updateFlight(stationType: StationType, station: Station) {
        val newFlight = if (stationType == StationType.FROM) {
            flightStorage.flight.value?.copy(from = station)
        } else {
            flightStorage.flight.value?.copy(to = station)
        }
        flightStorage.updateFlight(newFlight)
    }
}