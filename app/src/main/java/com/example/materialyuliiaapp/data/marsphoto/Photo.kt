package com.example.materialyuliiaapp.data.marsphoto


import com.example.materialyuliiaapp.data.marsPhotoAPI.Rover
import com.google.gson.annotations.SerializedName

data class Photo(

    val camera: Camera,
    @SerializedName("earth_date")
    val earthDate: String,
    val id: Int,
    @SerializedName("img_src")
    val imgSrc: String,
    val rover: Rover,
    val sol: Int
)