package com.example.materialyuliiaapp.ui.marsphoto

import com.example.materialyuliiaapp.data.marsphoto.MarsPhotoResponseData

sealed class MarsAppState {

    data class Success(val marsPhotoResponseData: MarsPhotoResponseData) : MarsAppState()
    data class Error(val error: Throwable) : MarsAppState()
    object Loading : MarsAppState()
}