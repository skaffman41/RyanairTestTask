package ru.alexnimas.ryanairtesttask.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexnimas.ryanairtesttask.data.response.FlightsResponse

interface FlightsApi {
    @GET("en-gb/Availability")
    fun getFlights(
        @Query("dateout") dateout: String,
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("adt") adt: Int,
        @Query("chd") chd: Int,
        @Query("teen") teen: Int,
        @Query("inf") inf: Int,
        @Query("ToUs") ToUs: String,
    ): Single<FlightsResponse>
}