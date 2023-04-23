package com.bouyahya.unsplash_multiplatform.data.remote

import io.ktor.client.*
import io.ktor.client.engine.android.*

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Android)
}