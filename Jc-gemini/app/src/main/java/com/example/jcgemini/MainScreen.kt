package com.example.jcgemini

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


@Composable
fun MainView(
    name: String,
    modifier: Modifier = Modifier // 1. Default value & 2. Reordered
) {
    Text(
        text = stringResource(R.string.greeting, name), // 3. String resources
        modifier = modifier // 4. Explicit naming
    )
}