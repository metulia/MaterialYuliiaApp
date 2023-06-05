package com.example.materialyuliiaapp.data.marsphoto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPhotoAPI {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsPhotoBySol(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String
    ): Call<MarsPhotoResponseData>
}