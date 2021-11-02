package com.example.image_handler.presentation.di

import com.example.image_handler.data.repository.DataSourceRepository
import com.example.image_handler.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideImageApi(repository: DataSourceRepository): Repository

}