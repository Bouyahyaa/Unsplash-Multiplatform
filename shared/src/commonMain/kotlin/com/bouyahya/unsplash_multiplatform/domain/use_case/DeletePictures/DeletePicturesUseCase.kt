package com.bouyahya.unsplash_multiplatform.domain.use_case.DeletePictures

import com.bouyahya.unsplash_multiplatform.domain.repository.PictureRepository

class DeletePicturesUseCase constructor(
    private val repository: PictureRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deletePicture(id = id)
    }
}