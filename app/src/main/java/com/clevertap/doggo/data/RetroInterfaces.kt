package com.clevertap.doggo.data

import com.clevertap.doggo.data.random.RandomResponse
import com.clevertap.doggo.utils.Constants
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap

class RetrofitInterfaces {

    interface RandomInterface {
        @GET(Constants.RANDOM_URL)
        fun getDog(): Call<RandomResponse>
    }

}