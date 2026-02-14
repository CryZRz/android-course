package com.example.jcretrofit.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.jcretrofit.R

@Composable
fun CrCoilImage(image: String, sizeRes: Int){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = rememberVectorPainter(Icons.Default.Timer),
        error = rememberVectorPainter(Icons.Default.BrokenImage),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(dimensionResource(sizeRes))
            .clip(CircleShape)
    )
}