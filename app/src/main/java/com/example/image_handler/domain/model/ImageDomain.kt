package com.example.image_handler.domain.model

import com.example.image_handler.data.db.entity.ImageEntity
import com.example.image_handler.presentation.model.ImageUI

data class ImageDomain(
    val title: String,
    val content: String,
    val dateTaken: String,
    val datePublished: String
)

fun ImageDomain.mapToData(): ImageEntity = ImageEntity(
    title = this.title,
    content = this.content,
    datePublished = this.datePublished,
    dateTaken = this.dateTaken
)

fun ImageDomain.mapToUi(): ImageUI = ImageUI(
    title = this.title,
    content = this.content,
    dateTaken = this.dateTaken,
    datePublished = this.datePublished
)