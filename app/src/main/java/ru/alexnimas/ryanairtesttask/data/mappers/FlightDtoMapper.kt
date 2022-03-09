package ru.alexnimas.ryanairtesttask.data.mappers

import ru.alexnimas.ryanairtesttask.data.response.FlightsResponse
import ru.alexnimas.ryanairtesttask.data.response.StationsResponse
import ru.alexnimas.ryanairtesttask.domain.model.Flight
import ru.alexnimas.ryanairtesttask.domain.model.Station
import ru.alexnimas.ryanairtesttask.domain.util.DomainMapper
import javax.inject.Inject

class FlightDtoMapper @Inject constructor() :
    DomainMapper<FlightsResponse.Trip.Date.FlightDto, Flight> {
    override fun mapToDomainModel(dtoModel: FlightsResponse.Trip.Date.FlightDto): Flight {
        return Flight(
            duration = dtoModel.duration,
            faresLeft = dtoModel.faresLeft,
            flightKey = dtoModel.flightKey,
            flightNumber = dtoModel.flightNumber,
            infantsLeft = dtoModel.infantsLeft,
            operatedBy = dtoModel.operatedBy,
            time = dtoModel.time,
            timeUTC = dtoModel.timeUTC
        )
    }
}