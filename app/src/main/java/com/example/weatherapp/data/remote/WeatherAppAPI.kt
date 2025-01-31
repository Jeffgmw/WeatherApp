package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.Response.CurrentWeatherResponse
import com.example.weatherapp.data.remote.Response.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAppAPI {

    @GET("forecast")
    fun getForecastByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<ForecastResponse>

    @GET("weather")
    fun getCurrentByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<CurrentWeatherResponse>
}
