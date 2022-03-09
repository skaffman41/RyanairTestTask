package ru.alexnimas.ryanairtesttask.domain.model

data class Flight(
    val duration: String,
    val faresLeft: Int,
    val flightKey: String,
    val flightNumber: String,
    val infantsLeft: Int,
    val operatedBy: String,
    val time: List<String>,
    val timeUTC: List<String>
)