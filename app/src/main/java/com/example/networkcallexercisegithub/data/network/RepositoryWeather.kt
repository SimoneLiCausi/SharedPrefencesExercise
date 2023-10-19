package com.example.networkcallexercisegithub.data.network

import com.example.networkcallexercisegithub.data.WeatherData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryWeather {
    var weatherEndPoint: WeatherService? = null


    suspend fun getWeather(): Response<WeatherData>? {
        if (weatherEndPoint == null) {
            weatherEndPoint = createRetrofitInstance().create(WeatherService::class.java)
        }
        return weatherEndPoint?.getWeather("Palermo IT")
    }

}


fun createRetrofitInstance(): Retrofit {

    val baseUrl = "http://api.weatherapi.com/v1/"
    val loggingInterceptor = HttpLoggingInterceptor()
    val authInterceptor = MyInterceptor()



    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


    val httpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
