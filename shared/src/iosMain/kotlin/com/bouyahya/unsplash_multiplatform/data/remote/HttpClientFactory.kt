package com.bouyahya.unsplash_multiplatform.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Darwin)
}