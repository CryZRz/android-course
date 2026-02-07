package com.example.lifecyclejc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.lifecyclejc.ui.theme.LifeCycleJCTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainPreview(){
    LifeCycleJCTheme {
        MainView(Modifier.padding(top = 24.dp), {}, {})
    }
}

@Composable
fun MainView(
    modifier: Modifier,
    onLaunchDialog: () -> Unit,
    onLaunchSecond: () -> Unit
){
    var tfOne by remember { mutableStateOf("") }
    var tfTwo by remember { mutableStateOf("") }

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {onLaunchSecond()}
        ) {
            Text("Second activity")
        }
        Button(
            onClick = {onLaunchDialog()}
        ) {
            Text("Dialog")
        }

        TextField(
            value = tfOne,
            onValueChange = {tfOne = it},
            modifier = Modifier.padding(top = dimensionResource(R.dimen.common_padding_default))
        )

        TextField(
            value = tfTwo,
            onValueChange = {tfTwo = it},
            modifier = Modifier.padding(top = dimensionResource(R.dimen.common_padding_default))
        )
    }
}