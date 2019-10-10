package com.swaptopup.codecraft.ui

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.swaptopup.codecraft.model.Results
import com.swaptopup.codecraft.util.DownloadImageTask
import com.swaptopup.codecraft.util.ImagesCache
import com.swaptopup.codecraft.util.MyApp
import com.swaptopup.codecraft.util.listen
import kotlinx.android.synthetic.main.single_list_item_view.view.*
import android.view.MotionEvent
import android.graphics.Color.parseColor
import android.R
import android.graphics.Color


//AIzaSyAKN7JyMlJ38gW-jgsMHQSOIIUxIMmUY6E
//,val iconList: ArrayList<String>,val nameList: ArrayList<String>,val addressList: ArrayList<String>
class RestraListAdapter(context: Context) : RecyclerView.Adapter<RestraListAdapter.ViewHolder>() {

    val iconList: ArrayList<String> = arrayListOf<String>()
    val nameList: ArrayList<String> = arrayListOf<String>()
    val addressList: ArrayList<String> = arrayListOf<String>()
    val latList: ArrayList<Double> = arrayListOf<Double>()
    val lngList: ArrayList<Double> = arrayListOf<Double>()
    val cache:ImagesCache= ImagesCache()


    var bitmap: Bitmap? = null
    lateinit var url:String
val context:Context=context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestraListAdapter.ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(com.swaptopup.codecraft.R.layout.single_list_item_view,parent,false)).listen{
            pos,type->
          RestraListActivity.latitude=latList.get(pos)
            RestraListActivity.longitude=lngList.get(pos)
            RestraListActivity.isSelect=true
                 }
    }

    override fun onBindViewHolder(holder: RestraListAdapter.ViewHolder, position: Int) {
        cache.initializeCache()


        holder?.name?.text= nameList!!.get(position)
//            nameList!!.get(position)
        holder?.address?.text=addressList!!.get(position)
//            addressList!!.get(position)
        url= iconList!!.get(position)
     Glide.with(context).load(url).into(holder?.icon)
//        holder.itemView.setOnTouchListener(v)
        holder.itemView.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.setBackgroundColor(Color.parseColor("#baffea"))
            }
            if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
                v.setBackgroundColor(Color.TRANSPARENT)
            }
            false
        })
//        bitmap= cache.getImageFromWarehouse(url)
//        if(bitmap!=null)
//            holder?.icon.setImageBitmap(bitmap)
//        else
//            holder?.icon.setImageBitmap(null)
//        var downloadImageTask:DownloadImageTask= DownloadImageTask(this ,300,300)
//        downloadImageTask.execute(url)


//        var ine:InputStream=URL(url).openStream()
//bitmap=BitmapFactory.decodeStream(ine)
//        holder?.icon?.setImageBitmap(bitmap)


    }

    override fun getItemCount(): Int {
        return nameList!!.size
    }

    fun updatePostList(dataList:List<Results>?){
    for(i in 0..dataList!!.size-1){

            this.nameList.add(dataList.get(i).name)
        this.addressList!! .add(dataList.get(i).vicinity)
            this.iconList !!.add(dataList.get(i).icon)
        this.lngList!!.add(dataList.get(i).geometry.location!!.longitude)
        this.latList!!.add(dataList.get(i).geometry.location!!.latitude)
    }
          notifyDataSetChanged()
    }

     class ViewHolder(view: View):RecyclerView.ViewHolder(view){
       val icon=view.icon_restra
        val name=view.name_view
        val address=view.address_view

    }
}



