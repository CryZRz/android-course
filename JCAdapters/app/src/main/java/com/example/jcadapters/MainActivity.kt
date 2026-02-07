package com.example.jcadapters

import android.os.Bundle
import android.widget.Toast
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
import com.example.jcadapters.ui.theme.JCAdaptersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCAdaptersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        Modifier.padding(innerPadding),
                        {
                            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
                        }
                        )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LocalPreview() {
    JCAdaptersTheme {
        MainPreview()
    }
}