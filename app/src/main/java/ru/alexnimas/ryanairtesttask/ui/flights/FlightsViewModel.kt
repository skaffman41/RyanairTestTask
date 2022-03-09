package ru.alexnimas.ryanairtesttask.ui.flights

import androidx.lifecycle.ViewModel
import ru.alexnimas.ryanairtesttask.domain.usecase.StationsUseCase

class FlightsViewModel(
    private val stationsUseCase: StationsUseCase
): ViewModel() {

    fun loadStations() {

    }
}