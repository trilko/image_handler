package com.example.image_handler.data.repository

import com.example.image_handler.data.db.dao.ImageDao
import com.example.image_handler.data.db.entity.ImageEntity
import com.example.image_handler.data.db.entity.mapToDomain
import com.example.image_handler.data.network.ImageApi
import com.example.image_handler.data.network.model.mapToDomain
import com.example.image_handler.domain.model.ImageDomain
import com.example.image_handler.domain.model.mapToData
import com.example.image_handler.domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DataSourceRepository @Inject constructor(
    private val network: ImageApi,
    private val database: ImageDao
) : Repository {

    override fun getImages(): Single<List<ImageDomain>> {
        return database.getImages()
            .flatMap { cachedImages ->
                getFromNetworkIfCacheIsEmpty(cachedImages)
            }
    }

    private fun getFromNetworkIfCacheIsEmpty(cachedImages: List<ImageEntity>): Single<List<ImageDomain>> {
        return if (cachedImages.isEmpty()) {
            network.getImagesApi()
                .map { it.mapToDomain()}
                .doOnSuccess { data -> saveImages(data) }
        } else {
            Single.just(cachedImages.map { image -> image.mapToDomain() })
        }
    }

    override fun saveImages(images: List<ImageDomain>): Completable {
        database.deleteImages()
        return database.putImages(images.map { image -> image.mapToData() })
    }

    override fun clearImages(): Completable {
        return database.deleteImages()
    }

}