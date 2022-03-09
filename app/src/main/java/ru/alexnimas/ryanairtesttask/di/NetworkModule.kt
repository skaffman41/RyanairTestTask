package ru.alexnimas.ryanairtesttask.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.alexnimas.ryanairtesttask.BuildConfig
import ru.alexnimas.ryanairtesttask.data.api.FlightsApi
import ru.alexnimas.ryanairtesttask.data.RepositoryImpl
import ru.alexnimas.ryanairtesttask.data.api.StationsApi
import ru.alexnimas.ryanairtesttask.data.mappers.StationDtoMapper
import ru.alexnimas.ryanairtesttask.domain.Repository

@Module(includes = [NetworkModule.BindsNetworkModule::class])
@InstallIn(ViewModelComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }
        return clientBuilder.build()
    }

    @STATIONS
    @Provides
    fun provideStationsRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(STATIONS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @FLIGHTS
    @Provides
    fun provideFlightsRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FLIGHTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideStationsApi(@STATIONS retrofit: Retrofit): StationsApi {
        return retrofit.create(StationsApi::class.java)
    }

    @Provides
    fun provideFlightsApi(@FLIGHTS retrofit: Retrofit): FlightsApi {
        return retrofit.create(FlightsApi::class.java)
    }

    companion object {
        const val STATIONS_BASE_URL = "https://mobile-testassets-dev.s3.eu-west-1.amazonaws.com/"
        const val FLIGHTS_BASE_URL = "https://www.ryanair.com/api/booking/v4/"
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    interface BindsNetworkModule {
        @Binds
        fun bindsRepository(repository: RepositoryImpl): Repository
    }
}