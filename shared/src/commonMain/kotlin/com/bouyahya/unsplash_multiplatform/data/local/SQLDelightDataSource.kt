package com.bouyahya.unsplash_multiplatform.data.local

import com.bouyahya.unsplash_multiplatform.UnsplashDatabase
import database.PictureEntity

class SQLDelightDataSource(
    database: UnsplashDatabase
) : PictureLocalDataSource {
    private val queries = database.pictureQueries

    override suspend fun insertPicture(picture: PictureEntity) {
        queries.insertPicture(picture)
    }

    override suspend fun getAllPictures(query: String): List<PictureEntity> {
        return queries
            .getAllPictures(query = query)
            .executeAsList()
    }

    override suspend fun deletePicture(id: String) {
        queries.deletePicture(id)
    }
}