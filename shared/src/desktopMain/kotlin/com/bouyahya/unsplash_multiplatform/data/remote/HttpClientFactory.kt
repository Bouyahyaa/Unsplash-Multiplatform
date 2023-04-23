package com.bouyahya.unsplash_multiplatform.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.*

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Java)
}