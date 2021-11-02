package com.example.image_handler.data.db.dao

import androidx.room.*
import com.example.image_handler.data.db.entity.ImageEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class ImageDao {

    @Query("SELECT * FROM IMAGE")
    abstract fun getImages(): Single<List<ImageEntity>>

    @Insert
    abstract fun putImages(imageEntities: List<ImageEntity>): Completable

    @Query("DELETE FROM IMAGE" )
    abstract fun deleteImages(): Completable

}