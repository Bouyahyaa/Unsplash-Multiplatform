package com.bouyahya.unsplash_multiplatform.ui.home

import com.bouyahya.unsplash_multiplatform.domain.model.Picture

data class HomeState(
    val pictures: List<Picture> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)