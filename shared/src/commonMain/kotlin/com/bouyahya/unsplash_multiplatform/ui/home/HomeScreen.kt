package com.bouyahya.unsplash_multiplatform.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bouyahya.unsplash_multiplatform.ui.home.components.PictureListItem
import com.bouyahya.unsplash_multiplatform.ui.home.components.SearchView
import com.bouyahya.unsplash_multiplatform.ui.home.components.StoryListItem

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val state = viewModel.state.value
    val text = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        LazyRow {
            items(state.pictures) { picture ->
                StoryListItem(
                    painterStoryImage = picture.large!!,
                    contentDescription = picture.description!!,
                    seen = false,
                )
            }
        }

        SearchView(state = text, onTextChanged = {
            viewModel.onEvent(HomeEvent.OnSearchQueryChange(text.value.text))
        })

        Spacer(modifier = Modifier.size(7.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.pictures) { picture ->
                    PictureListItem(
                        baseImage = picture.regular!!,
                        userImage = picture.small!!,
                        username = picture.username!!,
                        contentDescription = picture.description!!,
                        liked = picture.isLiked,
                        onDeleteClick = {
                            viewModel.onEvent(HomeEvent.DeletePicture(picture, text.value.text))
                        },
                        onLikeClick = {
                            viewModel.onEvent(
                                HomeEvent.LikePicture(picture.id)
                            )
                        },
                        onItemClick = {
                            viewModel.onEvent(HomeEvent.OnPictureClick(picture))
                        }
                    )
                }
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}