package com.bouyahya.unsplash_multiplatform.di

import com.bouyahya.unsplash_multiplatform.data.repository.PictureRepositoryImpl
import com.bouyahya.unsplash_multiplatform.domain.repository.PictureRepository
import com.bouyahya.unsplash_multiplatform.domain.use_case.DeletePictures.DeletePicturesUseCase
import com.bouyahya.unsplash_multiplatform.domain.use_case.getPictures.GetPicturesUseCase
import com.bouyahya.unsplash_multiplatform.domain.use_case.likePictures.LikePicturesUseCase
import org.koin.dsl.module

val dataModule = module {
    single<PictureRepository> {
        PictureRepositoryImpl(
            pictureLocalDataSource = get(),
            unsplashClient = get()
        )
    }
    single {
        GetPicturesUseCase(repository = get())
    }
    single {
        DeletePicturesUseCase(repository = get())
    }

    single {
        LikePicturesUseCase(repository = get())
    }
}