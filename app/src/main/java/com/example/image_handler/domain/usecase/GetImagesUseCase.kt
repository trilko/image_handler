package com.example.image_handler.domain.usecase

import com.example.image_handler.domain.model.ImageDomain
import com.example.image_handler.domain.model.SortedOrder
import com.example.image_handler.domain.model.SortedOrder.*
import com.example.image_handler.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(order: SortedOrder): Single<List<ImageDomain>> {

        val images = repository.getImages()

        return when (order) {
            NO_SORTED -> {
                images
            }
            PUBLISH_DATE -> {
                images.map { list ->
                    list.sortedBy { it.datePublished }
                }
            }
            TAKEN_DATE -> {
                images.map { list ->
                    list.sortedBy { it.dateTaken }
                }
            }
        }

    }

}