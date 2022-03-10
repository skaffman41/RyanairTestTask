package ru.alexnimas.ryanairtesttask.domain.util

interface PresentationMapper<DomainModel, PresentationModel> {
    fun mapToPresentationModel(domainModel: DomainModel): PresentationModel
}