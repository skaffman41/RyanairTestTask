package ru.alexnimas.ryanairtesttask.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.alexnimas.ryanairtesttask.data.api.FlightsApi
import ru.alexnimas.ryanairtesttask.data.api.StationsApi
import ru.alexnimas.ryanairtesttask.data.mappers.FlightDtoMapper
import ru.alexnimas.ryanairtesttask.data.mappers.StationDtoMapper
import ru.alexnimas.ryanairtesttask.domain.Repository
import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight
import ru.alexnimas.ryanairtesttask.domain.model.DomainStation
import ru.alexnimas.ryanairtesttask.domain.request.FlightsRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val stationsApi: StationsApi,
    private val flightsApi: FlightsApi,
    private val stationDtoMapper: StationDtoMapper,
    private val flightDtoMapper: FlightDtoMapper
) : Repository {
    override fun getStations(): Single<List<DomainStation>> {
        return stationsApi.getStations()
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.stations.map { station -> stationDtoMapper.mapToDomainModel(station) }
            }
    }

    override fun getFlights(flightsRequest: FlightsRequest): Single<List<DomainFlight>> {
        return flightsApi.getFlights(
            dateout = flightsRequest.dateout,
            origin = flightsRequest.origin,
            destination = flightsRequest.destination,
            adt = flightsRequest.adt,
            chd = flightsRequest.chd,
            teen = flightsRequest.teen,
            inf = flightsRequest.inf,
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