package com.example.networkcallexercisegithub.data.network

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response{
        val basicRequest = chain.request()



        val newRequest = basicRequest.newBuilder()
            .addHeader("key", "6a5cac82dea741b9b32153036232109")
            .build()


        return chain.proceed(newRequest)
    }

}