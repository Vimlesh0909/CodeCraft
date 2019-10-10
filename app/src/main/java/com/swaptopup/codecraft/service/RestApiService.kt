package com.swaptopup.codecraft.service

import com.swaptopup.codecraft.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on : Feb 25, 2019
 * Author     : AndroidWave
 */
interface RestApiService {

//location=12.9141417,74.8559568"
    @GET("json?type=restaurant&key=AIzaSyAKN7JyMlJ38gW-jgsMHQSOIIUxIMmUY6E%20&rankby=distance&")
    fun getRetraDetails(@Query("location")location:String): Call<Restaurant>

}
