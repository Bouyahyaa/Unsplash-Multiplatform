package com.bouyahya.unsplash_multiplatform.ui.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.bouyahya.unsplash_multiplatform.domain.model.Picture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PictureDetailsViewModel constructor(
    private val componentContext: ComponentContext,
    picture: Picture,
    private val backToHomeScreen: () -> Unit
) : ComponentContext by componentContext {
    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate)
    private val _state = mutableStateOf(PictureDetailsState())
    val state: State<PictureDetailsState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                picture = picture
            )
        }
    }

    fun onEvent(event: PictureDetailsEvent) {
        when (event) {
            is PictureDetailsEvent.OnBackPressed -> {
                backToHomeScreen.invoke()
            }
        }
    }
}