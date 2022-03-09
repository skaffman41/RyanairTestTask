package ru.alexnimas.ryanairtesttask.domain.model

data class FlightSearchModel(
    val from: Station? = null,
    val to: Station? = null,
    val flyOut: String? = null,
    val passengers: Passengers = Passengers()
)

data class Passengers(
    val adult: Int? = null,
    val children: Int? = null,
    val teen: Int? = null,
    val infant: Int? = null
)