package ru.alexnimas.ryanairtesttask.ui.model

data class Flight(
    val timeFrom: String,
    val timeTo: String,
    val codeFrom: String,
    val codeTo: String,
    val duration: String,
    val flightCode: String,
    val price: String
)