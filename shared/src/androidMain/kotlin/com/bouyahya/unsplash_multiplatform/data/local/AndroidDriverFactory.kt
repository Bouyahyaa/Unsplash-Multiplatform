package com.bouyahya.unsplash_multiplatform.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bouyahya.unsplash_multiplatform.UnsplashDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope


actual fun Scope.createDriver(): SqlDriver {
    return AndroidSqliteDriver(UnsplashDatabase.Schema, androidContext(), "unsplash.db")
}