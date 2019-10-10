package com.swaptopup.codecraft.ui

import android.app.Application
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.*
import com.swaptopup.codecraft.model.Results
import com.swaptopup.codecraft.network.HttpGetRequest
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.swaptopup.codecraft.model.Restaurant
import com.swaptopup.codecraft.util.MyApp


class RestraListActivityViewModel(): ViewModel() {
lateinit var location:Location

//
//    lateinit var results:Results
//    lateinit var response:String
// var restraListAdapter: RestraListAdapter? =null
    lateinit var arrayList:ArrayList<String>
    var httpGetRequest=HttpGetRequest()
    var currentLocation: MutableLiveData<Location>? = MutableLiveData()
    var networkRespone:MutableLiveData<String>?=MutableLiveData()

    fun updateLocation(location: Location?) {
        currentLocation?.value = location
    }
    fun getTheCurrentLocation():Location{
        return  location
    }


    //I would like to thank for giving me an opportunity to work in this position.
    //It was great working with the Robosoft team and througly enjoyed and appraciated the opportunity i got within the team.
    //Please feel free to let me know if there's anything else i can do to aid in my smooth transition, during my stay
    //I wish robosoft team members good health and all the suceess, and hope to stay in touch in the future

    fun getNetworkResponse():MutableLiveData<String>{
        networkRespone=httpGetRequest.reponsedata
//        var gson = GsonBuilder().setPrettyPrinting().create()
//        gson.fromJson(networkRespone!!.value, Restaurant::class.java)
//     var restaurant:Restaurant= Restaurant()
////        if(restaurant!=null)
////        Log.d("valueessss", restaurant.results?.get(2)?.name)
//        if(restraListAdapter==null)
//            restraListAdapter= RestraListAdapter()
//     restraListAdapter!!.updatePostList(restaurant)
        return networkRespone as MutableLiveData<String>
    }
    fun makeNetworkCall(){
        //Hi Team, I write to confirm that I would like to resign from my post. please accept this eamil as a formal notification of my resignation from the position of Software Engineer L2.
        //please release me as soon as possible (till 15 oct will be good helpful for me)
//because my friend started a company in Indore and I want to be part of so, I have to go and join him in Indore as early as possible in this month. so,
        //
//        httpGetRequest.execute("https//api.github.com/users/hadley/orgs")
//        https@ //api.github.com/users/hadley/orgs
        httpGetRequest.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?type=restaurant&key=AIzaSyAKN7JyMlJ38gW-jgsMHQSOIIUxIMmUY6E%20&rankby=distance&location=12.9141417,74.8559568")
    }

}

private fun Any.observe(context: Application, observer: Observer<String>) {

}


