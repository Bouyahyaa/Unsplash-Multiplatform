package com.bouyahya.unsplash_multiplatform.di

import com.bouyahya.unsplash_multiplatform.data.local.createDriver
import com.bouyahya.unsplash_multiplatform.data.local.SQLDelightDataSource
import com.bouyahya.unsplash_multiplatform.UnsplashDatabase
import com.bouyahya.unsplash_multiplatform.data.local.PictureLocalDataSource
import org.koin.dsl.module


val databaseModule = module {
    factory { createDriver() }
    single<PictureLocalDataSource> { SQLDelightDataSource(UnsplashDatabase(driver = get())) }
}