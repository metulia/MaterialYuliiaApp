package com.example.materialyuliiaapp.ui.marsphoto

import com.example.materialyuliiaapp.data.marsphoto.MarsPhotoAPI
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarsRepositoryImpl {

    private val baseUrl = "https://api.nasa.gov/"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        ).build()
    }

    fun getMarsPhotoBySolApi(): MarsPhotoAPI {
        return retrofit.create(MarsPhotoAPI::class.java)
    }
}