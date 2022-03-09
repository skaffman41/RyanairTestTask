package ru.alexnimas.ryanairtesttask.data.response

data class StationsResponse(
    val stations: List<StationDto>
) {
    data class StationDto(
        val alias: List<String>?,
        val alternateName: String?,
        val code: String?,
        val countryCode: String?,
        val countryGroupCode: String?,
        val countryGroupName: String?,
        val countryName: String?,
        val latitude: String?,
        val longitude: String?,
        val markets: List<Market>?,
        val mobileBoardingPass: Boolean?,
        val name: String?,
        val timeZoneCode: String?,
        val tripCardImageUrl: String?
    ) {
        data class Market(
            val code: String?,
            val group: String?
        )
    }
}