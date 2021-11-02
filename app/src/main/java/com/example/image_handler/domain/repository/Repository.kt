package com.example.image_handler.domain.repository

import com.example.image_handler.domain.model.ImageDomain
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {

    fun getImages(): Single<List<ImageDomain>>

    fun saveImages(images: List<ImageDomain>): Completable

    fun clearImages(): Completable

}