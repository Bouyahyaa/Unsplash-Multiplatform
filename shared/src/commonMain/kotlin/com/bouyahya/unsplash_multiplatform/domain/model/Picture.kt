package com.bouyahya.unsplash_multiplatform.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Picture(
    val id: String,
    val width: String?,
    val height: String?,
    val color: String?,
    val created_at: String?,
    val updated_at: String?,
    val description: String?,

    /** Urls **/
    val raw: String?,
    val full: String?,
    val regular: String?,
    val thumb: String?,

    /** User **/
    val idUser: String?,
    val username: String?,
    val name: String?,
    // ProfileImage
    val small: String?,
    val medium: String?,
    val large: String?,

    val likes: String?,
    val isLiked: Boolean = false,
) : Parcelable
