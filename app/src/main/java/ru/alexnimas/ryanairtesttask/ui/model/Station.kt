package ru.alexnimas.ryanairtesttask.ui.model

interface IStation

data class Station(
    val code: String,
    val countryName: String,
    val name: String
) : IStation

data class Country(
    val name: String
) : IStation, Comparable<Country> {
    override fun compareTo(other: Country): Int {
        return name.compareTo(other.name)
    }
}