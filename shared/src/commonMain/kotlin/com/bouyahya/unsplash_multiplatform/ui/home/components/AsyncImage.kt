package com.bouyahya.unsplash_multiplatform.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun AsyncImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(url)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = colorFilter,
            modifier = Modifier.fillMaxSize()
        )
    }
}