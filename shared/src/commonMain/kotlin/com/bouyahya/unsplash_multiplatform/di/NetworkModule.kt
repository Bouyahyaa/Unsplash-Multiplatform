package com.bouyahya.unsplash_multiplatform.di

import com.bouyahya.unsplash_multiplatform.data.remote.UnsplashClient
import com.bouyahya.unsplash_multiplatform.data.remote.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module get() = { enableLogging ->
    module {
        single { createHttpClient(enableLogging) }
        single { UnsplashClient(httpClient = get()) }
    }
}