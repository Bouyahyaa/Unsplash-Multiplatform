package com.bouyahya.unsplash_multiplatform.data.local

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.createDriver(): SqlDriver