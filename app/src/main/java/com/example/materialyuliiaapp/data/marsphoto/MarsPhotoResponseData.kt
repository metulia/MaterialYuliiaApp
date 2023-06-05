package com.example.materialyuliiaapp.data.marsphoto


import com.google.gson.annotations.SerializedName

data class MarsPhotoResponseData(
    @SerializedName("photos")
    val photos: List<Photo>
)