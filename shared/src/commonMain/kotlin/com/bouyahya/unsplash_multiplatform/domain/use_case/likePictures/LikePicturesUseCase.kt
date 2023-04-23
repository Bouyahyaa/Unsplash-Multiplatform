package com.bouyahya.unsplash_multiplatform.domain.use_case.likePictures

import com.bouyahya.unsplash_multiplatform.domain.model.Picture
import com.bouyahya.unsplash_multiplatform.domain.repository.PictureRepository

class LikePicturesUseCase constructor(
    private val repository: PictureRepository,
) {
    suspend operator fun invoke(picture: Picture) {
        repository.likePicture(picture = picture)
    }
}