package com.example.networkcallexercisegithub.data


import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("location")
    val location: Location?
)