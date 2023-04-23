package com.bouyahya.unsplash_multiplatform.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun StoryListItem(
    painterStoryImage: String,
    contentDescription: String,
    seen: Boolean,
) {
    Box(
        modifier = Modifier
            .height(90.dp)
            .width(93.5.dp)
            .padding(12.dp)
            .border(
                width = 2.dp,
                brush = if (seen) Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF707070),
                        Color(0xFF888888)
                    ),
                ) else Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFF71458),
                        Color(0xFFFA95AC)
                    ),
                ),
                shape = RoundedCornerShape(100)
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(CircleShape),
            url = painterStoryImage,
            contentDescription = contentDescription,
        )
    }
}