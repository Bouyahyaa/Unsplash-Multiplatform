package com.bouyahya.unsplash_multiplatform.ui

import androidx.compose.runtime.Composable
import com.bouyahya.unsplash_multiplatform.ui.theme.MyApplicationTheme
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*
import com.bouyahya.unsplash_multiplatform.ui.details.PictureDetailsScreen
import com.bouyahya.unsplash_multiplatform.ui.home.HomeScreen


@Composable
internal fun UnsplashRoot(component: UnsplashRootComponent) {
    MyApplicationTheme {
        Children(stack = component.stack, animation = stackAnimation(fade() + scale())) {
            when (val child = it.instance) {
                is UnsplashRootComponent.Child.Home -> HomeScreen(child.component)
                is UnsplashRootComponent.Child.Details -> PictureDetailsScreen(child.component)
            }
        }
    }
}