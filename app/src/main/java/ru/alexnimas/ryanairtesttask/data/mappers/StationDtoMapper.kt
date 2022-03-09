package ru.alexnimas.ryanairtesttask.data.mappers

import ru.alexnimas.ryanairtesttask.data.response.StationsResponse
import ru.alexnimas.ryanairtesttask.domain.model.Station
import ru.alexnimas.ryanairtesttask.domain.util.DomainMapper
import javax.inject.Inject

class StationDtoMapper @Inject constructor() : DomainMapper<StationsResponse.StationDto, Station> {
    override fun mapToDomainModel(dtoModel: StationsResponse.StationDto): Station {
        return Station(
            code = dtoModel.code.orEmpty(),
            countryName = dtoModel.countryName.orEmpty(),
            name = dtoModel.name.orEmpty()
        )
    }
}