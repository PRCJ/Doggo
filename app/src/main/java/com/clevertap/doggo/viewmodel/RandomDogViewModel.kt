package com.clevertap.doggo.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clevertap.doggo.R
import com.clevertap.doggo.data.random.RandomApiClient
import com.clevertap.doggo.data.random.RandomResponse
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomDogViewModel : ViewModel() {
    private var context : Context ?=null
    private val _randomDog = MutableLiveData<RandomResponse>()
    private val _randomDogError = MutableLiveData<String>()
    val RandomDog: LiveData<RandomResponse> = _randomDog
    val RandomDogError: LiveData<String> = _randomDogError


    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadRandomDog(context: Context) {
        this.context =context
        _isViewLoading.postValue(true)
        var call: Call<RandomResponse>? = null

        call = RandomApiClient.build()?.getDog()
        call?.enqueue(object : Callback<RandomResponse> {
            override fun onFailure(call: Call<RandomResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<RandomResponse>, response: Response<RandomResponse>
            ) {
                _isViewLoading.postValue(false)

                if (response.code() == 200){
                    if (response.body()!!.message!=null ){
                        _randomDog.postValue(response.body())
                    }else {
                        _randomDog.postValue(response.body())
                        _isEmptyList.postValue(true)
                    }

                }else if (response.code() == 400){
                    if (response.errorBody()!=null) {
                        var jObject = JSONObject(response.errorBody()!!.string())
                        Log.d("random dog==>",jObject.toString())
                        _randomDogError.postValue(jObject.getString("m"))
                        _isEmptyList.postValue(true)

                    }else {
                        _isEmptyList.postValue(true)
                        _onMessageError.postValue(context.getString(R.string.internal_404_error))

                    }

                }else if (response.code() == 500){
                    _isEmptyList.postValue(true)
                    _onMessageError.postValue(context.getString(R.string.internal_404_error))
                }else if (response.code() == 502){
                    _isEmptyList.postValue(true)
                    _onMessageError.postValue(context.getString(R.string.internal_404_error))
                }else if (response.code() == 404){
                    _isEmptyList.postValue(true)
                    _onMessageError.postValue(context.getString(R.string.internal_404_error))
                }
            }
        })

    }

}

