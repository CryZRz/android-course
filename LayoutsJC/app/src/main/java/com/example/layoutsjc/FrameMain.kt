package com.example.layoutsjc

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutsjc.ui.theme.LayoutsJCTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScrollLinearPreview(){
    LayoutsJCTheme {
        FrameView(Modifier.padding(top = 24.dp))
    }
}

@Composable
 fun FrameView(modifier: Modifier){
    Box(
        modifier.fillMaxSize()
            .padding(16.dp)
            .background(Color.Red)
    ){
        Image(
            painter = painterResource(R.drawable.megadeth),
            contentDescription = "Album de megatdeh",
            modifier = Modifier.fillMaxWidth()
                .height(180.dp)
                .background(Color.Magenta)
        )
        Text(
            "Texto demostrativo",
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .background(Color.White)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            TextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Green
                ),
                label = {
                    Text("Escribe tu apellido")
                },
                modifier = Modifier
            )
        }

        Box(modifier = Modifier.fillMaxWidth()
            .height(180.dp),
            contentAlignment = Alignment.BottomEnd
        ){
            Image(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
                    .background(Color.LightGray)
                    .clickable{}
            )

            Image(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 56.dp, bottom = 16.dp)
                    .background(Color.LightGray)
                    .clickable{}
            )
        }
    }
}