package ru.alexnimas.ryanairtesttask.domain

import androidx.lifecycle.LiveData
import ru.alexnimas.ryanairtesttask.domain.model.FlightSearchModel

interface FlightStorage {
    val flight: LiveData<FlightSearchModel?>
    fun updateFlight(flightSearchModel: FlightSearchModel?)
}