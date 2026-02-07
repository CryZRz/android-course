package com.example.lifecyclejc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lifecyclejc.ui.theme.LifeCycleJCTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifeCycleJCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExoPlayerView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        Log.i("Second LifeCycle", "onCreate")
        //player = ExoPlayer.Builder(this).build()
        //binding.playerView.player = player
    }

    override fun onStart() {
        super.onStart()
        Log.i("Second lifeCylce", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.i("Second lifeCylce", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Second lifeCylce", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Second lifeCylce", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Second lifeCylce", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Second lifeCylce", "onDestroy")
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    LifeCycleJCTheme {
        ExoPlayerView(Modifier)
    }
}