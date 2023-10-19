package com.example.networkcallexercisegithub.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//STEP 2


//Creato il provider, mettiamo dentro la logica retrofit.

class WeatherProvider {

    private val baseUrl = "https://api.weatherapi.com/v1/"
    private val loggingInterceptor = HttpLoggingInterceptor()
    private val authInterceptor = MyInterceptor()

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // Per fare funzionare le chiamate, serve il service adesso, quindi
    // chiamiamolo così:

    private val service = retrofit.create(WeatherService::class.java)


    fun provide(): WeatherService {
        return service
    }

    // Finito di creare il provider, andiamolo a caricare nell'appplication

}