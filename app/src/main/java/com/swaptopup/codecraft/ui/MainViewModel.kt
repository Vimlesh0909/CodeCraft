package com.swaptopup.codecraft.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.swaptopup.codecraft.model.Results
import com.swaptopup.codecraft.service.DataRepository

/**
 * Created on : Feb 26, 2019
 * Author     : AndroidWave
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dataRepository: DataRepository

    fun restaDetails(latlng: String): MutableLiveData<List<Results>>{
      return dataRepository.getMutableLiveData(latlng)}

    init {
        dataRepository = DataRepository(application)
    }


}
