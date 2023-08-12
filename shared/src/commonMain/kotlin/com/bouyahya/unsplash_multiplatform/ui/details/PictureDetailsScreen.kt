package com.bouyahya.unsplash_multiplatform.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun PictureDetailsScreen(
    viewModel: PictureDetailsViewModel,
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            state.picture?.large?.let { it1 -> rememberAsyncImagePainter(it1) }?.let { it2 ->
                Image(
                    painter = it2,
                    contentDescription = "Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)
                )
            }
            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = state.picture?.name ?: "Picture Name",
                color = Color.White
            )

            Spacer(Modifier.height(10.dp))

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = state.picture?.description ?: "No Description",
                color = Color.White
            )
        }
    }
}