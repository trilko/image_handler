package com.example.image_handler.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.image_handler.data.db.ImageDatabase
import com.example.image_handler.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, ImageDatabase::class.java, DB_NAME).build()

}