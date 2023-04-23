package com.bouyahya.unsplash_multiplatform.domain.use_case.getPictures

import com.bouyahya.unsplash_multiplatform.core.Resource
import com.bouyahya.unsplash_multiplatform.domain.model.Picture
import com.bouyahya.unsplash_multiplatform.domain.repository.PictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPicturesUseCase constructor(
    private val repository: PictureRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Picture>>> = flow {
        try {
            emit(Resource.Loading<List<Picture>>())
            val pictures = repository.getPictures(query)
            emit(Resource.Success<List<Picture>>(pictures))
        } catch (e: Exception) {
            emit(Resource.Error<List<Picture>>(e.message ?: "An unexpected error occur"))
        }
    }
}
