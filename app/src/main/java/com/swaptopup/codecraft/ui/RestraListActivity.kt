package com.swaptopup.codecraft.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.transition.Visibility
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.swaptopup.codecraft.R
import com.swaptopup.codecraft.model.Restaurant
import com.swaptopup.codecraft.model.Results
import com.swaptopup.codecraft.util.SimpleDividerItemDecoration

import kotlinx.android.synthetic.main.activity_restra_list.*
import kotlinx.android.synthetic.main.content_restra_list.*

class RestraListActivity : AppCompatActivity() {
 var restraListActivity=RestraListActivityViewModel()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    companion object{
        var latitude:Double = 0.0
        var longitude:Double = 0.0
        var isSelect:Boolean=false
    }

    private val MY_LOCATION_REQUEST_CODE=101
    private var mainViewModel: MainViewModel? = null
    val restraListAdapter: RestraListAdapter=RestraListAdapter(this)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restra_list)
        val actionBar=supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
swiperefresh.setOnRefreshListener { getwheatherData("$latitude,$longitude") }
//        setSupportActionBar(toolbar)
        checkForPermission(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
restraListActivity= ViewModelProviders.of(this).get(RestraListActivityViewModel::class.java)
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
           R.id.f_item ->{
               if(isSelect)
               { val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)}
               else
                   Toast.makeText(this, "Please choose/click one of Restaurants", Toast.LENGTH_LONG).show()
            }
                               else ->
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
    @SuppressLint("MissingPermission")
    private fun obtieneLocalizacion(){
        fusedLocationClient=FusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                latitude = location?.latitude!!
                longitude = location?.longitude

                getwheatherData("$latitude,$longitude")
                Toast.makeText(this,"$latitude,$longitude",Toast.LENGTH_LONG).show()

            }
    }

    fun checkForPermission(context: Context){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

Toast.makeText(this,"location Permission is granted",Toast.LENGTH_LONG).show()
            obtieneLocalizacion()
        } else {
            // Show rationale and request permission.
//            showAlert()
//            Toast.makeText(this,"location Permission is not grandet",Toast.LENGTH_LONG).show()
            requestPermissions(
             this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_LOCATION_REQUEST_CODE)

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
if(requestCode==MY_LOCATION_REQUEST_CODE){
    obtieneLocalizacion()
}

    }


    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Location permission")
        builder.setMessage("Location permissions are required to do the task.")
        builder.setPositiveButton("OK", { dialog, which -> requestPermissions() })
        builder.setNeutralButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }
    private fun requestPermissions() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Show an explanation asynchronously
            requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_LOCATION_REQUEST_CODE)
        } else {


        }
    }
    fun getwheatherData(latlng: String) {

        mainViewModel!!.restaDetails(latlng).observe(this,
            Observer<List<Results>> { dataList -> prepareRecyclerView(dataList)})
    }

    @SuppressLint("WrongConstant")
    private fun prepareRecyclerView(dataList:List<Results>?) {
        progress_loader.visibility=View.GONE

        Log.d("ressssssssss", dataList.toString());
swiperefresh.isRefreshing=false
        restraListAdapter.updatePostList(dataList)
        var s:ConstraintLayout
        var linearLayoutManager:LinearLayoutManager= LinearLayoutManager(this)

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager)


        recyclerView.addItemDecoration((SimpleDividerItemDecoration(this)))
        recyclerView.setAdapter(restraListAdapter)



        }
    }




