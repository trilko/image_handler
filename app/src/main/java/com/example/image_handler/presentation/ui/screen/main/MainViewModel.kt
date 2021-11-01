package com.example.image_handler.presentation.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.image_handler.data.network.ImageApi
import com.example.image_handler.data.network.model.ImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageApi: ImageApi,
) : ViewModel() {

    private val _liveData = MutableLiveData<ImageResponse>()
    val liveData: LiveData<ImageResponse> = _liveData

    fun updateData() {
        val dispose = imageApi.getImagesApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data -> getSomething(data)},
                {error -> handleError(error)}
            )

    }

    private fun getSomething(data: ImageResponse) {
        _liveData.value = data
    }

    private fun handleError(error: Throwable) {
        val new = error
    }

}