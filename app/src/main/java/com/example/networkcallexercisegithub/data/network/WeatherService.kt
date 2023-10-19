package com.example.networkcallexercisegithub.data.network

import com.example.networkcallexercisegithub.data.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("current.json")
    suspend fun getWeather(@Query ("q") query: String) : Response<WeatherData>

}