package ru.alexnimas.ryanairtesttask.ui.mappers

import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight
import ru.alexnimas.ryanairtesttask.domain.util.PresentationMapper
import ru.alexnimas.ryanairtesttask.ui.model.Flight
import java.text.SimpleDateFormat
import javax.inject.Inject

class FlightsMapper @Inject constructor() : PresentationMapper<DomainFlight, Flight> {
    override fun mapToPresentationModel(domainModel: DomainFlight): Flight {
        return Flight(
            timeFrom = mapToUiTime(domainModel.time.getOrNull(0).orEmpty()),
            timeTo = mapToUiTime(domainModel.time.getOrNull(1).orEmpty()),
            codeFrom = domainModel.segments.getOrNull(0)?.origin.orEmpty(),
            codeTo = domainModel.segments.getOrNull(0)?.destination.orEmpty(),
            duration = domainModel.segments.getOrNull(0)?.duration.orEmpty(),
            flightCode = domainModel.segments.getOrNull(0)?.flightNumber.orEmpty(),
            price = (domainModel.regularFare.fares.getOrNull(0)?.amount ?: 0.0).toString()
        )
    }

    private fun mapToUiTime(timeString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val date = dateFormat.parse(timeString)
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(date)
    }
}
