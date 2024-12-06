package com.example.recipefinder.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(
    private val apiKey: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val requestWithApiKey = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(requestWithApiKey)
    }
}
