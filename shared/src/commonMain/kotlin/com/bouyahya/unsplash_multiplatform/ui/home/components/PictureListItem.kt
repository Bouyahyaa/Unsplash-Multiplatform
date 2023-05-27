package com.bouyahya.unsplash_multiplatform.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
internal fun PictureListItem(
    baseImage: String,
    userImage: String,
    username: String,
    contentDescription: String,
    liked: Boolean,
    onDeleteClick: () -> Unit,
    onLikeClick: () -> Unit,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable {
            onItemClick()
        }, shape = RoundedCornerShape(15.dp), elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {

            AsyncImage(
                url = baseImage,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ), startY = 300f
                        )
                    )
                    .fillMaxSize()
            ) {
                IconButton(
                    onClick = {
                        onDeleteClick()
                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Picture",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                UserImageNameItem(
                    userImage = userImage,
                    username = username,
                    contentDescription = contentDescription
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                IconButton(
                    onClick = {
                        onLikeClick()
                    },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .padding(start = 15.dp)
                            .alpha(alpha = if (liked) 1f else 0.5f),
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Love Picture",
                        tint = if (liked) Color.Red else Color.LightGray,
                    )
                }
            }
        }
    }
}