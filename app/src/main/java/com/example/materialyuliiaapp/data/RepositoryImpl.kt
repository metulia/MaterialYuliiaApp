package com.example.materialyuliiaapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.*

class RepositoryImpl {

    private val baseUrl = "https://api.nasa.gov/"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        ).build()
    }

    fun getPictureOfTheDayApi(): PictureOfTheDayAPI {
        return retrofit.create(PictureOfTheDayAPI::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateYesterday(): String {
        var today: LocalDate = LocalDate.now()
        var yesterday: LocalDate = today.minusDays(1)
        return yesterday.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateDayBeforeYesterday(): String {
        var today: LocalDate = LocalDate.now()
        var yesterday: LocalDate = today.minusDays(2)
        return yesterday.toString()
    }
}