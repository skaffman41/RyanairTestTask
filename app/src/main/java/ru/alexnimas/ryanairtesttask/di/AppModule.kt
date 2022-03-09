package ru.alexnimas.ryanairtesttask.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexnimas.ryanairtesttask.data.FlightStorageImpl
import ru.alexnimas.ryanairtesttask.domain.FlightStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    @Singleton
    fun provideFlightStorage(flightStorage: FlightStorageImpl): FlightStorage
}