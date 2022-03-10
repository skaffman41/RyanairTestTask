package ru.alexnimas.ryanairtesttask.domain

import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.Single
import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight
import ru.alexnimas.ryanairtesttask.domain.model.DomainStation
import ru.alexnimas.ryanairtesttask.domain.request.FlightsRequest

@ViewModelScoped
interface Repository {
    fun getStations(): Single<List<DomainStation>>

    fun getFlights(flightsRequest: FlightsRequest): Single<List<DomainFlight>>
}