package com.lucasqrib.cats.platform.network

import com.lucasqrib.cats.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

private const val AUTH_FIELD = "x-api-key"

object CatsHttpClient {
    fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(authInterceptor)
        return okHttpClientBuilder.build()
    }


    private val authInterceptor =
        { interceptor: Interceptor.Chain ->
            val request: Request = interceptor.request()

            val newRequest: Request = request.newBuilder()
                .addHeader(AUTH_FIELD, BuildConfig.API_KEY)
                .build()
            interceptor.proceed(newRequest)
        }


}