package ru.alexnimas.ryanairtesttask.data.api

import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.Single
import retrofit2.http.GET
import ru.alexnimas.ryanairtesttask.data.response.StationsResponse

@ViewModelScoped
interface StationsApi {
    @GET("stations.json")
    fun getStations(): Single<StationsResponse>
}