package com.bouyahya.unsplash_multiplatform.ui.details

sealed class PictureDetailsEvent {
    object OnBackPressed : PictureDetailsEvent()
}
