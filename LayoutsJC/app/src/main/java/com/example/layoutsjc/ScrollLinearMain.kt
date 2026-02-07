package com.example.layoutsjc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutsjc.ui.theme.LayoutsJCTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScrollLinearPreview(){
    LayoutsJCTheme {
        ScrollLinerView(Modifier.padding(top = 24.dp))
    }
}

@Composable
fun ScrollLinerView(modifier: Modifier){
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
            .background(Color.Yellow)
            .fillMaxWidth()
            ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "wrap content",
            modifier = Modifier.background(Color.Cyan).
            padding(horizontal = 16.dp)
            )
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
            Text("Match parent",
                Modifier.fillMaxWidth()
                    .background(Color.Blue)
                    .padding(start = 16.dp, end = 16.dp)
            )
        }
        Button(onClick = {}) {
            Text("Button")
        }
        Button(onClick = {}) {
            Text("Button")
        }
        Button(onClick = {}) {
            Text("Button")
        }
        Button(onClick = {}) {
            Text("Button")
        }
        Button(onClick = {}) {
            Text("Button")
        }
        Button(onClick = {}) {
            Text("Button")
        }
        Button(onClick = {}) {
            Text("Button")
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.size(width = 40.dp, height = 24.dp)
                    .padding(end = 16.dp)
                    .background(Color.Green)
            ) {  }
            Button(onClick = {}) {
                Text("Button")
            }
            Button(onClick = {}, Modifier.padding(16.dp)) {
                Text("Button")
            }
            Button(onClick = {}) {
                Text("Button")
            }
        }
    }
}