package com.bouyahya.unsplash_multiplatform.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bouyahya.unsplash_multiplatform.UnsplashDatabase
import org.koin.core.scope.Scope


actual fun Scope.createDriver(): SqlDriver {
    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    UnsplashDatabase.Schema.create(driver)
    return driver
}