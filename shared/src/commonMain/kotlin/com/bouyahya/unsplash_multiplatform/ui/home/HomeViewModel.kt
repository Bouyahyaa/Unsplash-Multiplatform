package com.bouyahya.unsplash_multiplatform.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.bouyahya.unsplash_multiplatform.core.Resource
import com.bouyahya.unsplash_multiplatform.domain.use_case.DeletePictures.DeletePicturesUseCase
import com.bouyahya.unsplash_multiplatform.domain.use_case.getPictures.GetPicturesUseCase
import com.bouyahya.unsplash_multiplatform.domain.use_case.likePictures.LikePicturesUseCase
import kotlinx.coroutines.*

class HomeViewModel constructor(
    private val componentContext: ComponentContext,
    private val getPicturesUseCase: GetPicturesUseCase,
    private val deletePicturesUseCase: DeletePicturesUseCase,
    private val likePicturesUseCase: LikePicturesUseCase
) : ComponentContext by componentContext {
    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate)
    private var searchJob: Job? = null
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getPictures("")
        lifecycle.doOnDestroy(viewModelScope::cancel)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {

            is HomeEvent.LikePicture -> {
                viewModelScope.launch {
                    val pictures = _state.value.pictures.map { picture ->
                        if (picture.id == event.id) {
                            likePicturesUseCase.invoke(picture.copy(isLiked = !picture.isLiked))
                            picture.copy(isLiked = !picture.isLiked)
                        } else {
                            picture
                        }
                    }
                    _state.value = state.value.copy(
                        pictures = pictures
                    )
                }
            }

            is HomeEvent.OnSearchQueryChange -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getPictures(
                        query = event.query
                    )
                }
            }

            is HomeEvent.DeletePicture -> {
                viewModelScope.launch {
                    deletePicturesUseCase.invoke(event.picture.id)
                    val pictures = _state.value.pictures.filter {
                        it.id != event.picture.id
                    }
                    _state.value = state.value.copy(
                        pictures = pictures
                    )
                }
            }
        }
    }

    private fun getPictures(query: String) {
        viewModelScope.launch {
            getPicturesUseCase.invoke(query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = HomeState(
                            pictures = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Error -> _state.value = HomeState(
                        error = result.message ?: "An unexpected error occur ",
                    )
                    is Resource.Loading -> {
                        _state.value = HomeState(
                            isLoading = true,
                        )
                    }
                }
            }
        }
    }
}