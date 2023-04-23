package com.bouyahya.unsplash_multiplatform.data.local

import database.PictureEntity

interface PictureLocalDataSource {
    suspend fun insertPicture(picture: PictureEntity)
    suspend fun getAllPictures(query: String): List<PictureEntity>
    suspend fun deletePicture(id: String)
}