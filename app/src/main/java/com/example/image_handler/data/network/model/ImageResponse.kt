package com.example.image_handler.data.network.model

data class ImageResponse(
    private val title: String,
    private val items: List<Item>
)

data class Item(
    private val title: String,
    private val link: String,
    private val media: Media,
    private val date_taken: String,
    private val description: String,
    private val published: String,
    private val author: String,
    private val author_id: String,
    private val tags: String
)

data class Media(
    private val m: String
)