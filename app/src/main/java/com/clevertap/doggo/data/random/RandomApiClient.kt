package com.clevertap.doggo.data.random

import com.clevertap.doggo.data.RetrofitInterfaces
import com.clevertap.doggo.utils.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit


object RandomApiClient {

    private var RandomApiinterface: RetrofitInterfaces.RandomInterface? = null

    fun build(): RetrofitInterfaces.RandomInterface? {

        var builder: Retrofit.Builder = App.getRetrofit()

        var httpClient: OkHttpClient.Builder =App.getOkHttpClient()

        var retrofit: Retrofit = builder.client(httpClient.build()).build()

        RandomApiinterface = retrofit.create(RetrofitInterfaces.RandomInterface::class.java)

        return RandomApiinterface as RetrofitInterfaces.RandomInterface
    }

}