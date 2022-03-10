package ru.alexnimas.ryanairtesttask.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.Single
import ru.alexnimas.ryanairtesttask.domain.Repository
import ru.alexnimas.ryanairtesttask.domain.model.DomainFlight
import ru.alexnimas.ryanairtesttask.domain.request.FlightsRequest
import javax.inject.Inject

@ViewModelScoped
class FlightsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(flightsRequest: FlightsRequest): Single<List<DomainFlight>> {
        return repository.getFlights(flightsRequest)
    }
}