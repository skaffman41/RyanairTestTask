package ru.alexnimas.ryanairtesttask.domain.util

interface DomainMapper<DtoModel, DomainModel> {
    fun mapToDomainModel(dtoModel: DtoModel): DomainModel
}