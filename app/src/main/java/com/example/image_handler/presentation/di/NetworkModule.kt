package com.example.image_handler.presentation.di

import com.example.image_handler.data.network.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): ImageApi = retrofit.create(ImageApi::class.java)

}