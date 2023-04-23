package com.bouyahya.unsplash_multiplatform.data.remote

import com.bouyahya.unsplash_multiplatform.core.Constants
import com.bouyahya.unsplash_multiplatform.data.remote.dto.PictureDto
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UnsplashClient(
    private val httpClient: HttpClient
) {
    suspend fun getRemotePictures(): List<PictureDto> {
        return httpClient.get("${Constants.BASE_URL}photos/?client_id=${Constants.APP_ID}&page=${1}&per_page=${30}&order_by=sort") {
            contentType(ContentType.Application.Json)
        }.body<List<PictureDto>>()
    }
}