package com.example.materialyuliiaapp.ui.picture

import android.os.Build
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendRequestByDateYesterday() {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayApi()
            .getPictureOfTheDayByDate(NASA_API_KEY, repositoryImpl.getDateYesterday())
            .enqueue(callback)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendRequestByDateBeforeYesterday() {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayApi()
            .getPictureOfTheDayByDate(NASA_API_KEY, repositoryImpl.getDateDayBeforeYesterday())
            .enqueue(callback)
    }

    private val callback = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(AppState.Success(response.body()!!))
            } else {
                liveData.postValue(AppState.Error(Throwable()))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            liveData.postValue(AppState.Error(t))
        }
    }
}