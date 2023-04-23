package com.bouyahya.unsplash_multiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.bouyahya.unsplash_multiplatform.Application
import com.bouyahya.unsplash_multiplatform.di.initKoin
import com.bouyahya.unsplash_multiplatform.ui.UnsplashRootComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin {
            androidContext(applicationContext)
            androidLogger()
        }

        val root = UnsplashRootComponent(defaultComponentContext())
        setContent {
            Application(component = root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}