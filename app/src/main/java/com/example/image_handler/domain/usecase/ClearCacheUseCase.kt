package com.example.image_handler.domain.usecase

import com.example.image_handler.domain.repository.Repository
import io.reactivex.Completable
import javax.inject.Inject

class ClearCacheUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(): Completable = repository.clearImages()

}