package com.example.image_handler.data.network

import com.example.image_handler.data.network.model.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ImageApi {

    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    fun getImagesApi(): Single<ImageResponse>

}