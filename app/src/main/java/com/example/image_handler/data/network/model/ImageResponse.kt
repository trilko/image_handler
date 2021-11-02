package com.example.image_handler.data.network.model

import com.example.image_handler.data.db.entity.ImageEntity
import com.example.image_handler.domain.model.ImageDomain
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val title: String,
    val items: List<Item>
)

data class Item(
    val title: String,
    val link: String,
    val media: Media,
//    @SerializedName("date_taken")
    val date_taken: String,
    val description: String,
//    @SerializedName("published")
    val published: String,
    val author: String,
//    @SerializedName("author_id")
    val author_id: String,
    val tags: String
)

data class Media(
    val m: String
)

fun ImageResponse.mapToDomain(): List<ImageDomain> {
    return this.items.map { item ->
        ImageDomain(
            title = item.title,
            content = item.media.m,
            datePublished = item.published,
            dateTaken = item.date_taken
        )
    }
}

fun ImageResponse.mapToDatabase(): List<ImageEntity> {
    return this.items.map { item ->
        ImageEntity(
            title = item.title,
            content = item.media.m,
            datePublished = item.published,
            dateTaken = item.date_taken
        )
    }
}