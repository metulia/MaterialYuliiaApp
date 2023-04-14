package com.example.materialyuliiaapp.ui

import com.example.materialyuliiaapp.data.PictureOfTheDayResponseData

sealed class AppState {

    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
