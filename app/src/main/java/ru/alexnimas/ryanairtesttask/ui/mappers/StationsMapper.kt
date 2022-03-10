package ru.alexnimas.ryanairtesttask.ui.mappers

import ru.alexnimas.ryanairtesttask.domain.model.DomainStation
import ru.alexnimas.ryanairtesttask.domain.util.PresentationMapper
import ru.alexnimas.ryanairtesttask.ui.model.Station
import javax.inject.Inject

class StationsMapper @Inject constructor() : PresentationMapper<DomainStation, Station> {
    override fun mapToPresentationModel(domainModel: DomainStation): Station {
        return Station(
            code = domainModel.code,
            countryName = domainModel.countryName,
            name = domainModel.name
        )
    }

    fun mapToDomainModel(presentationModel: Station): DomainStation {
        return DomainStation(
            code = presentationModel.code,
            countryName = presentationModel.countryName,
            name = presentationModel.name
        )
    }
}
