package ru.alexnimas.ryanairtesttask.data.mappers

import ru.alexnimas.ryanairtesttask.data.response.StationsResponse
import ru.alexnimas.ryanairtesttask.domain.model.DomainStation
import ru.alexnimas.ryanairtesttask.domain.util.DomainMapper
import javax.inject.Inject

class StationDtoMapper @Inject constructor() : DomainMapper<StationsResponse.StationDto, DomainStation> {
    override fun mapToDomainModel(dtoModel: StationsResponse.StationDto): DomainStation {
        return DomainStation(
            code = dtoModel.code.orEmpty(),
            countryName = dtoModel.countryName.orEmpty(),
            name = dtoModel.name.orEmpty()
        )
    }
}