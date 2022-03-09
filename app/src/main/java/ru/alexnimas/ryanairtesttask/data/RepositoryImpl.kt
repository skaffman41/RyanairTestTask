package ru.alexnimas.ryanairtesttask.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.alexnimas.ryanairtesttask.data.api.FlightsApi
import ru.alexnimas.ryanairtesttask.data.api.StationsApi
import ru.alexnimas.ryanairtesttask.data.mappers.FlightDtoMapper
import ru.alexnimas.ryanairtesttask.data.mappers.StationDtoMapper
import ru.alexnimas.ryanairtesttask.data.response.FlightsResponse
import ru.alexnimas.ryanairtesttask.domain.Repository
import ru.alexnimas.ryanairtesttask.domain.model.Flight
import ru.alexnimas.ryanairtesttask.domain.model.Station
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val stationsApi: StationsApi,
    private val flightsApi: FlightsApi,
    private val stationDtoMapper: StationDtoMapper,
    private val flightDtoMapper: FlightDtoMapper
) : Repository {
    override fun getStations(): Single<List<Station>> {
        return stationsApi.getStations()
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.stations.map { station -> stationDtoMapper.mapToDomainModel(station) }
            }
    }

    override fun getFlights(
        dateout: String,
        origin: String,
        destination: String,
        adt: Int,
        chd: Int,
        teen: Int,
        inf: Int,
    ): Single<List<Flight>> {
        return flightsApi.getFlights(
            dateout = dateout,
            origin = origin,
            destination = destination,
            adt = 1,
            chd = chd,
            teen = teen,
            inf = inf,
            ToUs = "AGREED"
        )
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.trips.firstOrNull()?.dates?.firstOrNull()?.flights.orEmpty()
                    .map { flightDto ->
                        flightDtoMapper.mapToDomainModel(flightDto)
                    }
            }
    }
}