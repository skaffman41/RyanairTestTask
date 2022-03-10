package ru.alexnimas.ryanairtesttask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainFlight(
    val duration: String,
    val faresLeft: Int,
    val flightKey: String,
    val flightNumber: String,
    val infantsLeft: Int,
    val operatedBy: String,
    val regularFare: RegularFare,
    val segments: List<Segment>,
    val time: List<String>,
    val timeUTC: List<String>
) : Parcelable {
    @Parcelize
    data class RegularFare(
        val fareClass: String,
        val fareKey: String,
        val fares: List<Fare>
    ) : Parcelable {
        @Parcelize
        data class Fare(
            val amount: Double,
            val count: Int,
            val discountAmount: Double,
            val discountInPercent: Int,
            val hasBogof: Boolean,
            val hasDiscount: Boolean,
            val hasPromoDiscount: Boolean,
            val publishedFare: Double,
            val type: String
        ) : Parcelable
    }

    @Parcelize
    data class Segment(
        val destination: String,
        val duration: String,
        val flightNumber: String,
        val origin: String,
        val segmentNr: Int,
        val time: List<String>,
        val timeUTC: List<String>
    ) : Parcelable
}
