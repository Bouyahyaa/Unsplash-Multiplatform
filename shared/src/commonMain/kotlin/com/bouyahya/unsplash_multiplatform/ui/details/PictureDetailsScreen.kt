package com.bouyahya.unsplash_multiplatform.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun PictureDetailsScreen(
    viewModel: PictureDetailsViewModel
) {
    val state = viewModel.state.value
    Content(state, viewModel)
}

@Composable
internal fun Content(state: PictureDetailsState, viewModel: PictureDetailsViewModel) {
    Scaffold(
        backgroundColor = Color.Black,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                title = {
                    Text(
                        state.picture?.name ?: "PictureName",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(PictureDetailsEvent.OnBackPressed)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "BackToHomeScreen",
                            tint = Color.White
                        )
                    }
                })
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().background(color = Color.Red)) {
            state.picture?.large?.let { it1 -> rememberAsyncImagePainter(it1) }?.let { it2 ->
                Image(
                    painter = it2,
                    contentDescription = "Picture",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}