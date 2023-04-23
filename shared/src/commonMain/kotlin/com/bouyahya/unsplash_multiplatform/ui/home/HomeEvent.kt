package com.bouyahya.unsplash_multiplatform.ui.home

import com.bouyahya.unsplash_multiplatform.domain.model.Picture

sealed class HomeEvent {
    data class OnSearchQueryChange(val query: String) : HomeEvent()
    data class DeletePicture(val picture: Picture, val query: String) : HomeEvent()
    data class LikePicture(val id: String) : HomeEvent()
}