package ru.alexnimas.ryanairtesttask.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.Single
import ru.alexnimas.ryanairtesttask.domain.Repository
import ru.alexnimas.ryanairtesttask.domain.model.DomainStation
import javax.inject.Inject

@ViewModelScoped
class StationsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Single<List<DomainStation>> {
        return repository.getStations()
    }
}