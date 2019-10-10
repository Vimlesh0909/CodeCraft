package com.swaptopup.codecraft.service

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.swaptopup.codecraft.model.Restaurant
import com.swaptopup.codecraft.model.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

/**
 * Created on : Feb 26, 2019
 * Author     : AndroidWave
 */
class DataRepository(private val application: Application) {
    private var restraList = ArrayList<Results>()
    private val mutableLiveData = MutableLiveData<List<Results>>()

    fun getMutableLiveData(latlng: String): MutableLiveData<List<Results>> {

        val apiService = RetrofitInstance.apiService

        val call = apiService.getRetraDetails(latlng)
        call.enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                var restaurant: Restaurant = Restaurant()
                restaurant= response.body()!!
                restraList = restaurant.results as ArrayList<Results>
                mutableLiveData.value=restraList
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                // Log error here since request failed
                Log.e("error", t.toString())
            }
        })


        return mutableLiveData
    }
}
