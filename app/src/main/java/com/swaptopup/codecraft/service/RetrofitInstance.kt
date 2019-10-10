package com.swaptopup.codecraft.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created on : Feb 25, 2019
 * Author     : AndroidWave
 */
object RetrofitInstance {

    private var retrofit: Retrofit? = null

    val apiService: RestApiService
        get() {
            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!.create(RestApiService::class.java)

        }

}
