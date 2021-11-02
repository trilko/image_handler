package com.example.image_handler.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.image_handler.data.db.dao.ImageDao
import com.example.image_handler.data.db.entity.ImageEntity
import com.example.image_handler.utils.DB_VERSION

@Database(entities = [ImageEntity::class], version = DB_VERSION)
abstract class ImageDatabase: RoomDatabase() {

    abstract fun imageDao(): ImageDao

}