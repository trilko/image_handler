package com.example.image_handler.data.db.dao

import androidx.room.*
import com.example.image_handler.data.db.entity.Image
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class ImageDao {

    @Query("SELECT * FROM IMAGE")
    abstract fun getImages(): Single<List<Image>>

    @Insert
    abstract fun putImage(image: Image): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateImage(image: Image): Completable

    @Delete
    abstract fun deleteImage(image: Image): Completable

}