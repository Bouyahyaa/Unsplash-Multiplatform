package com.bouyahya.unsplash_multiplatform

import androidx.compose.ui.window.Application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.bouyahya.unsplash_multiplatform.di.initKoin
import com.bouyahya.unsplash_multiplatform.ui.UnsplashRoot
import com.bouyahya.unsplash_multiplatform.ui.UnsplashRootComponent
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    val rootComponent =
        UnsplashRootComponent(
            componentContext = DefaultComponentContext(lifecycle = LifecycleRegistry())
        )
    LifecycleRegistry().resume()

    return Application("My iOS") {
        UnsplashRoot(rootComponent)
    }
}