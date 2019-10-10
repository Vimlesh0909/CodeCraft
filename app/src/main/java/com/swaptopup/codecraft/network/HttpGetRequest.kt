package com.swaptopup.codecraft.network

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.swaptopup.codecraft.model.Restaurant
import com.swaptopup.codecraft.model.Results
import com.swaptopup.codecraft.ui.RestraListAdapter

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException as IoIOException

class HttpGetRequest : AsyncTask<String, Void, String>() {
//    val restraListAdapter: RestraListAdapter=RestraListAdapter()
//    val restaurant: Restaurant = Restaurant()

   var reponsedata:MutableLiveData<String>?= MutableLiveData()
    override fun doInBackground(vararg params: String): String? {
        val stringUrl = params[0]
        var result: String?
        var inputLine: String
        try {
            //Create a URL object holding our url
//            val myUrl = URL(stringUrl)         //Create a connection
//            val connection = myUrl.openConnection() as HttpURLConnection         //Set methods and timeouts
            val connection = URL(stringUrl).openConnection() as HttpURLConnection
            val data = connection.inputStream.bufferedReader().readText()

            result = data

        } catch (e: IoIOException) {
            e.printStackTrace()
            result = null
        }

        return result
    }

    override fun onPostExecute(result: String) {

//        val gson = GsonBuilder().setPrettyPrinting().create()
//        gson.fromJson(reponsedata.value, Results::class.java)
        geturl(result)
        this.reponsedata?.value =result
        super.onPostExecute(reponsedata?.value!!)
    }

    companion object {
        val REQUEST_METHOD = "GET"
        val READ_TIMEOUT = 15000
        val CONNECTION_TIMEOUT = 15000
    }
    fun geturl(respone:String){
        var gson = GsonBuilder().setPrettyPrinting().create()
        gson.fromJson(respone, Restaurant::class.java)
//        if(restraListAdapter!=null)
//                   restraListAdapter.updatePostList(restaurant)

    }
   }