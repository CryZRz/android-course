package com.example.jcroom.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.jcroom.R

@Composable
fun ProgressFullScreen(){
    Box(
        Modifier.fillMaxSize()
            .background(colorResource(R.color.progress_background))
            .clickable(interactionSource = null, indication = null){},
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}