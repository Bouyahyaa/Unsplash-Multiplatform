package com.bouyahya.unsplash_multiplatform.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.bouyahya.unsplash_multiplatform.UnsplashDatabase
import org.koin.core.scope.Scope


actual fun Scope.createDriver(): SqlDriver {
    return NativeSqliteDriver(UnsplashDatabase.Schema, "unsplash.db")
}