package ru.alexnimas.ryanairtesttask.data.response

data class FlightsResponse(
    val currPrecision: Int,
    val currency: String,
    val routeGroup: String,
    val serverTimeUTC: String,
    val termsOfUse: String,
    val tripType: String,
    val trips: List<Trip>,
    val upgradeType: String
) {
    data class Trip(
        val dates: List<Date>,
        val destination: String,
        val destinationName: String,
        val origin: String,
        val originName: String,
        val routeGroup: String,
        val tripType: String,
        val upgradeType: String
    ) {
        data class Date(
            val dateOut: String,
            val flights: List<FlightDto>
        ) {
            data class FlightDto(
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
            ) {
                data class RegularFare(
                    val fareClass: String,
                    val fareKey: String,
                    val fares: List<Fare>
                ) {
                    data class Fare(
                        val amount: Int,
                        val count: Int,
                        val discountAmount: Int,
                        val discountInPercent: Int,
                        val hasBogof: Boolean,
                        val hasDiscount: Boolean,
                        val hasPromoDiscount: Boolean,
                        val publishedFare: Int,
                        val type: String
                    )
                }

                data class Segment(
                    val destination: String,
                    val duration: String,
                    val flightNumber: String,
                    val origin: String,
                    val segmentNr: Int,
                    val time: List<String>,
                    val timeUTC: List<String>
                )
            }
        }
    }
}