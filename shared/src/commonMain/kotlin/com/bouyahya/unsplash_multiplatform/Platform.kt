package com.bouyahya.unsplash_multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform