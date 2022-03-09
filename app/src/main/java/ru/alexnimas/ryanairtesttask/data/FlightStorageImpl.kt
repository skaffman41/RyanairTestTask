package ru.alexnimas.ryanairtesttask.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.alexnimas.ryanairtesttask.domain.FlightStorage
import ru.alexnimas.ryanairtesttask.domain.model.FlightSearchModel
import javax.inject.Inject

class FlightStorageImpl @Inject constructor() : FlightStorage {
    private val _flight: MutableLiveData<FlightSearchModel?> = MutableLiveData(FlightSearchModel())
    override val flight: LiveData<FlightSearchModel?>
        get() = _flight

    override fun updateFlight(flightSearchModel: FlightSearchModel?) {
        _flight.postValue(flightSearchModel)
    }
}