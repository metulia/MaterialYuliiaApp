package com.example.materialyuliiaapp.ui.marsphoto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialyuliiaapp.BuildConfig
import com.example.materialyuliiaapp.data.marsphoto.MarsPhotoResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsFragmentViewModel(
    private val liveData: MutableLiveData<MarsAppState> = MutableLiveData(),
    private val repositoryImpl: MarsRepositoryImpl = MarsRepositoryImpl()
) :
    ViewModel() {

    fun getLiveData(): MutableLiveData<MarsAppState> {
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(MarsAppState.Loading)
        repositoryImpl.getMarsPhotoBySolApi().getMarsPhotoBySol(1000, BuildConfig.NASA_API_KEY)
            .enqueue(callback)
    }

    private val callback = object : Callback<MarsPhotoResponseData> {
        override fun onResponse(
            call: Call<MarsPhotoResponseData>,
            response: Response<MarsPhotoResponseData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(MarsAppState.Success(response.body()!!))
            }
        }

        override fun onFailure(call: Call<MarsPhotoResponseData>, t: Throwable) {
            liveData.postValue(MarsAppState.Error(t))
        }
    }
}