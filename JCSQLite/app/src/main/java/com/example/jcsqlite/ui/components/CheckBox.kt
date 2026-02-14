package com.example.jcsqlite.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcsqlite.ui.theme.JCSQLiteTheme
import com.example.jcsqlite.R

@Preview(showBackground = true)
@Composable
private fun CrCheckboxPreview(){
    JCSQLiteTheme {
        CrCheckBox(true) { }
    }
}

@Composable
fun CrCheckBox(checked: Boolean, onCheckedChange: (Boolean) -> Unit){
    IconButton(
        onClick = {onCheckedChange(!checked)}
    ) {
        Icon(
            painterResource(R.drawable.ic_humidity),
            contentDescription = null,
            tint = colorResource(R.color.purple_200)
            )
        AnimatedVisibility(
            visible = checked,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                painterResource(R.drawable.ic_humidity_high),
                contentDescription = null,
                tint = colorResource(R.color.purple_200)
            )
        }
    }
}