package ru.alexnimas.ryanairtesttask.data.mappers

import ru.alexnimas.ryanairtesttask.data.response.FlightsResponse
import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight
import ru.alexnimas.ryanairtesttask.domain.util.DomainMapper
import javax.inject.Inject

class FlightDtoMapper @Inject constructor() :
    DomainMapper<FlightsResponse.Trip.Date.Flight, DomainFlight> {
    override fun mapToDomainModel(dtoModel: FlightsResponse.Trip.Date.Flight): DomainFlight {
        return DomainFlight(
            duration = dtoModel.duration,
            faresLeft = dtoModel.faresLeft,
            flightKey = dtoModel.flightKey,
            flightNumber = dtoModel.flightNumber,
            infantsLeft = dtoModel.infantsLeft,
            operatedBy = dtoModel.operatedBy,
            time = dtoModel.time,
            timeUTC = dtoModel.timeUTC,
            regularFare = DomainFlight.RegularFare(
                fareClass = dtoModel.regularFare.fareClass,
                fareKey = dtoModel.regularFare.fareKey,
                fares = dtoModel.regularFare.fares.map {
                    DomainFlight.RegularFare.Fare(
                        amount = it.amount,
                        count = it.count,
                        discountAmount = it.discountAmount,
                        discountInPercent = it.discountInPercent,
                        hasBogof = it.hasBogof,
                        hasDiscount = it.hasDiscount,
                        hasPromoDiscount = it.hasPromoDiscount,
                        publishedFare = it.publishedFare,
                        type = it.type
                    )
                }
            ),
            segments = dtoModel.segments.map {
                DomainFlight.Segment(
                    destination = it.destination,
                    duration = it.duration,
                    flightNumber = it.flightNumber,
                    origin = it.origin,
                    segmentNr = it.segmentNr,
                    time = it.time,
                    timeUTC = it.timeUTC
                )
            }
        )
    }
}