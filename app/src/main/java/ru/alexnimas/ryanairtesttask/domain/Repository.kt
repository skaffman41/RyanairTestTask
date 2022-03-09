package ru.alexnimas.ryanairtesttask.domain

import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.Single
import ru.alexnimas.ryanairtesttask.data.response.FlightsResponse
import ru.alexnimas.ryanairtesttask.domain.model.Flight
import ru.alexnimas.ryanairtesttask.domain.model.Station

@ViewModelScoped
interface Repository {
    fun getStations(): Single<List<Station>>

    fun getFlights(
        dateout: String,
        origin: String,
        destination: String,
        adt: Int,
        chd: Int,
        teen: Int,
        inf: Int,
    ): Single<List<Flight>>
}