package com.clevertap.doggo.utils

import android.app.Application
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {

        fun getRetrofit(): Retrofit.Builder {
            val client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES).addInterceptor(interceptor()).build()
            return Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
//                .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
        }
        fun getOkHttpClient(): OkHttpClient.Builder {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            var httpClient: OkHttpClient.Builder = OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES).addInterceptor(interceptor())
            httpClient.addInterceptor(logging) // <-- this is the important line!


            return httpClient
        }

        private fun interceptor(): Interceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

    }

    override fun onCreate() {
        super.onCreate()
    }
}