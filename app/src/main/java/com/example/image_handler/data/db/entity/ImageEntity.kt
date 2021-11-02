package com.example.image_handler.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.image_handler.domain.model.ImageDomain
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image")
data class ImageEntity(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "dateTaken")
    val dateTaken: String,

    @ColumnInfo(name = "datePublished")
    val datePublished: String

)

fun ImageEntity.mapToDomain(): ImageDomain = ImageDomain(
    title = this.title,
    content = this.content,
    dateTaken = this.dateTaken,
    datePublished = this.datePublished
)