package com.swaptopup.codecraft.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Restaurant {

    @SerializedName("html_attributions")
    @Expose
    var htmlAttributions: List<Any>? = null
    @SerializedName("next_page_token")
    @Expose
    var nextPageToken: String? = null
    @SerializedName("results")
    @Expose
    var results: List<Results>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

}