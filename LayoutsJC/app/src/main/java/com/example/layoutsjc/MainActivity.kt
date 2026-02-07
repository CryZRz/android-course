package com.example.layoutsjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutsjc.ui.theme.LayoutsJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutsJCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LayoutsView(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun LayoutsView(modifier: Modifier){
    ConstraintView(modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LayoutsJCTheme {
        LayoutsView(Modifier.padding(top = 24.dp))
    }
}