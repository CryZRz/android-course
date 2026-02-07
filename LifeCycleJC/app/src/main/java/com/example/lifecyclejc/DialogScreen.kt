package com.example.lifecyclejc

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lifecyclejc.ui.theme.LifeCycleJCTheme
import com.example.lifecyclejc.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun DialogPreview(){
    LifeCycleJCTheme {
        DialogView()
    }
}

@Composable
fun DialogView(modifier: Modifier = Modifier){
    Text(
        "Hola mundo",
    style = Typography.headlineMedium
    )
}