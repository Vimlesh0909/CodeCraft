package com.swaptopup.codecraft.model


data class Results (

    var geometry : Geometry,
    var icon : String,
    var id : String,
    var name : String,
    var place_id : String,
    var reference : String,
    var scope : String,
    var types : List<String>,
    var vicinity : String
)