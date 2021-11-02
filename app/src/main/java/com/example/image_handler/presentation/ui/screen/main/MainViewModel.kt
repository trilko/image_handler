package com.example.image_handler.presentation.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.image_handler.domain.model.ImageDomain
import com.example.image_handler.domain.model.SortedOrder
import com.example.image_handler.domain.model.SortedOrder.*
import com.example.image_handler.domain.model.mapToUi
import com.example.image_handler.domain.usecase.GetImagesUseCase
import com.example.image_handler.presentation.model.ErrorUI
import com.example.image_handler.presentation.model.ImageUI
import com.example.image_handler.presentation.model.mapToErrorUI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _liveData = MutableLiveData<List<ImageUI>>()
    val liveData: LiveData<List<ImageUI>> = _liveData

    private val _liveError = MutableLiveData<ErrorUI>()
    val liveError: LiveData<ErrorUI> = _liveError

    fun getImages(sortedOrder: SortedOrder) {
        val dispose = getImagesUseCase.execute(sortedOrder)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data -> _liveData.value = data.map { imageDomain -> imageDomain.mapToUi() }},
                {error -> _liveError.value = error.mapToErrorUI("Something went wrong...")}
            )

        compositeDisposable.add(dispose)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}