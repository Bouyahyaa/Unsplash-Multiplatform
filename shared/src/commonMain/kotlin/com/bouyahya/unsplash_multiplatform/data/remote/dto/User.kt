package com.bouyahya.unsplash_multiplatform.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val profile_image: ProfileImage,
    val username: String
)