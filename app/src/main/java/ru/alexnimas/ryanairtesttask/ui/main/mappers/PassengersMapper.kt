package ru.alexnimas.ryanairtesttask.ui.main.mappers

import ru.alexnimas.ryanairtesttask.domain.model.Passengers
import javax.inject.Inject

class PassengersMapper @Inject constructor() {
    fun mapPassengers(pass: Passengers): String {
        val passengers: String = if (
            pass.adult == null && pass.children == null && pass.teen == null && pass.infant == null
        ) {
            ""
        } else {
            val adults = pass.adult?.let { "$it Adult" }
            val child = pass.children?.let { "$it Child" }
            val teen = pass.teen?.let { "$it Teen" }
            val infant = pass.infant?.let { "$it Infant" }
            listOf(adults, child, teen, infant).joinToString()
        }
        return passengers
    }
}