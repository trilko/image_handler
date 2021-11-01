package com.example.image_handler.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.image_handler.data.db.dao.ImageDao
import com.example.image_handler.data.db.entity.Image

@Database(entities = [Image::class], version = 1)
abstract class ImageDatabase: RoomDatabase() {

    abstract fun imageDao(): ImageDao

}