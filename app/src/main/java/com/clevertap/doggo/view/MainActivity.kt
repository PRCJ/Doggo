package com.clevertap.doggo.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.clevertap.doggo.data.random.RandomResponse
import com.clevertap.doggo.utils.Utils
import com.clevertap.doggo.viewmodel.RandomDogViewModel

import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.clevertap.doggo.R

import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Callback
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:RandomDogViewModel
    val dogImages= ArrayList<String>()
    val TAG= "CONSOLE"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initApi()
        if(dogImages.size<=1)
            prev.visibility = View.GONE

        prev.setOnClickListener {
            if(dogImages.size<=1)
                prev.visibility = View.GONE
            else {
                Picasso.get().load(dogImages[dogImages.size - 2]).fit().centerInside()
                    .into(dog, object : Callback {
                        override fun onSuccess() {
                            pbDog.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                        }

                    })

                pbDog.visibility = View.VISIBLE
                dogImages.removeAt(dogImages.size - 1)
                if(dogImages.size<=1)
                    prev.visibility = View.GONE
            }
        }
        next.setOnClickListener {
            pbDog.visibility = View.VISIBLE
            initApi()
            prev.visibility = View.VISIBLE
        }

    }
    private fun initApi() {
        viewModel= ViewModelProviders.of(this).get(RandomDogViewModel::class.java)
        viewModel.RandomDogError.observe(this, renderRandomDogErr0r)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
        viewModel.RandomDog.observe(this, renderRandomDog)
        fetchRandomDog()
    }
    private val renderRandomDog = Observer<RandomResponse> {
        dogImages.add(it.message)
        Picasso.get().load(it.message).fit().centerInside()
            .into(dog, object : Callback {
                override fun onSuccess() {
                    pbDog.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                }

            })
    }
    private val renderRandomDogErr0r = Observer<String> {
        Toast.makeText(this,it,Toast.LENGTH_SHORT).show();
    }

    private val isViewLoadingObserver = Observer<Boolean> {
            Utils(this).logD(TAG, "isViewLoading $it")
    }
    private val onMessageErrorObserver = Observer<Any> {
            Utils(this).logD(TAG, "onMessageError $it")

    }
    private val emptyListObserver = Observer<Boolean> {
            Utils(this).logD(TAG, "emptyListObserver $it")

    }

    private fun fetchRandomDog(
    ) {
        if (this.let { Utils(it).verifyAvailableNetwork() }!!) {
            viewModel.loadRandomDog(this)
        }
    }

}