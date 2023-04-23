package com.bouyahya.unsplash_multiplatform.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProfileImage(
    val large: String,
    val medium: String,
    val small: String
)