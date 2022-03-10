package ru.alexnimas.ryanairtesttask.ui.stations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.alexnimas.ryanairtesttask.domain.FlightStorage
import ru.alexnimas.ryanairtesttask.domain.usecase.StationsUseCase
import ru.alexnimas.ryanairtesttask.ui.main.StationType
import ru.alexnimas.ryanairtesttask.ui.mappers.StationsMapper
import ru.alexnimas.ryanairtesttask.ui.model.Country
import ru.alexnimas.ryanairtesttask.ui.model.IStation
import ru.alexnimas.ryanairtesttask.ui.model.Station
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val stationsUseCase: StationsUseCase,
    private val flightStorage: FlightStorage,
    private val stationsMapper: StationsMapper
) : ViewModel() {
    private val disposables = CompositeDisposable()
    val searchSubject = PublishSubject.create<String>()

    private var data = emptyList<Station>()

    private val _stations = MutableLiveData<List<IStation>>()
    val stations: LiveData<List<IStation>>
        get() = _stations

    init {
        subscribeOnSearchField()
        loadStations()
    }

    private fun subscribeOnSearchField() {
        disposables.add(
            searchSubject
                .subscribeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .map { query ->
                    data.filter { station ->
                        station.name.lowercase().contains(query.lowercase())
                    }
                }
                .map { addStickyHeaders(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _stations.postValue(it)
                }
        )
    }

    private fun loadStations() {
        disposables.add(
            stationsUseCase()
                .map { stations -> stations.map { stationsMapper.mapToPresentationModel(it) } }
                .doAfterSuccess { stations -> data = stations }
                .map { addStickyHeaders(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ stations ->
                    _stations.postValue(stations)
                }, {
                    Log.d("TAGF", "Here we can handle some errors, for example show toast, etc.")
                })
        )
    }

    private fun addStickyHeaders(stations: List<Station>): List<IStation> {
        val stationsMap = TreeMap<String, MutableList<Station>>()
        stations.forEach {
            if (stationsMap.containsKey(it.countryName)) {
                stationsMap[it.countryName]?.add(it)
            } else {
                stationsMap[it.countryName] = mutableListOf(it)
            }
        }
        val iStations = mutableListOf<IStation>()
        stationsMap.forEach {
            iStations.add(Country(name = it.key))
            iStations.addAll(it.value)
        }
        return iStations
    }

    fun updateFlight(stationType: StationType, station: Station) {
        val newFlight = if (stationType == StationType.FROM) {
            flightStorage.flight.value?.copy(from = stationsMapper.mapToDomainModel(station))
        } else {
            flightStorage.flight.value?.copy(to = stationsMapper.mapToDomainModel(station))
        }
        flightStorage.updateFlight(newFlight)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}