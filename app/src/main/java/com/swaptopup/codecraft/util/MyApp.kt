package com.swaptopup.codecraft.util

import android.app.Application
import android.content.Context


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }
init{
        instance=this
    }
    companion object {

        private var instance: Application?=Application()


            fun getContext():Application{
return instance as Application
            }
    }

}




//import android.app.Application
//import android.content.Context
//
//class GetContext:Application() {
//    companion object{
//        var context:Context? = null
//
//    }
//    override fun onCreate() {
//        super.onCreate()
//        context= context?:applicationContext
//    }
//
//    fun getContext(): Context? {
//        return context
//
//}}