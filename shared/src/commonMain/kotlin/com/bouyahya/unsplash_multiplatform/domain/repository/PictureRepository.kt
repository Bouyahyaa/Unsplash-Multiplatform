package com.bouyahya.unsplash_multiplatform.domain.repository

import com.bouyahya.unsplash_multiplatform.domain.model.Picture

interface PictureRepository {
    suspend fun getPictures(query: String): List<Picture>
    suspend fun deletePicture(id: String)
    suspend fun likePicture(picture: Picture)
}