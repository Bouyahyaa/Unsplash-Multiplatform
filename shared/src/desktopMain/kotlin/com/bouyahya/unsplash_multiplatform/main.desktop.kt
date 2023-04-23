package com.bouyahya.unsplash_multiplatform

import androidx.compose.runtime.Composable
import com.bouyahya.unsplash_multiplatform.ui.UnsplashRoot
import com.bouyahya.unsplash_multiplatform.ui.UnsplashRootComponent

@Composable
fun Application(unsplashRootComponent: UnsplashRootComponent) {
    UnsplashRoot(unsplashRootComponent)
}