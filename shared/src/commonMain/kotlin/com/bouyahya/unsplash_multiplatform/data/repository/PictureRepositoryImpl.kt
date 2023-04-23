package com.bouyahya.unsplash_multiplatform.data.repository

import com.bouyahya.unsplash_multiplatform.data.local.PictureLocalDataSource
import com.bouyahya.unsplash_multiplatform.data.mapper.toPicture
import com.bouyahya.unsplash_multiplatform.data.mapper.toPictureEntity
import com.bouyahya.unsplash_multiplatform.data.remote.UnsplashClient
import com.bouyahya.unsplash_multiplatform.data.remote.dto.PictureDto
import com.bouyahya.unsplash_multiplatform.domain.model.Picture
import com.bouyahya.unsplash_multiplatform.domain.repository.PictureRepository

class PictureRepositoryImpl(
    private var pictureLocalDataSource: PictureLocalDataSource,
    private var unsplashClient: UnsplashClient
) : PictureRepository {
    override suspend fun getPictures(query: String): List<Picture> {
        val localPictures = pictureLocalDataSource.getAllPictures(query)
        var remotePictures: List<PictureDto> = emptyList()

        val isDbEmpty = localPictures.isEmpty() && query.isBlank()
        val shouldJustLoadFromCache = !isDbEmpty

        if (!shouldJustLoadFromCache) {
            try {
                remotePictures = unsplashClient.getRemotePictures()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            remotePictures.let { pictures ->
                pictures.map {
                    pictureLocalDataSource.insertPicture(it.toPictureEntity())
                }
            }
        }

        return pictureLocalDataSource.getAllPictures(query).map {
            it.toPicture()
        }
    }

    override suspend fun deletePicture(id: String) {
        pictureLocalDataSource.deletePicture(id)
    }

    override suspend fun likePicture(picture: Picture) {
        pictureLocalDataSource.insertPicture(picture.toPictureEntity())
    }
}