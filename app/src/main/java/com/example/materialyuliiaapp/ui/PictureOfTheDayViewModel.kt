package com.example.materialyuliiaapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialyuliiaapp.BuildConfig.NASA_API_KEY
import com.example.materialyuliiaapp.data.PictureOfTheDayResponseData
import com.example.materialyuliiaapp.data.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData(): MutableLiveData<AppState> {
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDay(NASA_API_KEY).enqueue(callback)
    }

    fun sendRequestByDateYesterday() {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDayByDate(NASA_API_KEY, "2023-04-17").enqueue(callback)
    }

    fun sendRequestByDateBeforeYesterday() {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDayByDate(NASA_API_KEY, "2023-04-16").enqueue(callback)
    }

    private val callback = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(AppState.Success(response.body()!!))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            liveData.postValue(AppState.Error(t))
        }
    }
}