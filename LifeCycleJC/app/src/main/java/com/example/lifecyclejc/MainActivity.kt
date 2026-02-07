package com.example.lifecyclejc

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.lifecyclejc.ui.theme.LifeCycleJCTheme

class MainActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifeCycleJCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        Modifier.padding(innerPadding),
                        onLaunchDialog = {
                            startActivity(Intent(this, DialogActivity::class.java))
                        },
                        onLaunchSecond = {
                            startActivity(Intent(this, SecondActivity::class.java))
                        }
                        )
                }
            }
        }

        Log.i("LifeCycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("lifeCylce", "onStart")
        mediaPlayer = MediaPlayer.create(this, R.raw.song)
    }

    override fun onResume() {
        super.onResume()
        Log.i("lifeCylce", "onResume")
        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        Log.i("lifeCylce", "onPause")
        //mediaPlayer?.pause()

        mediaPlayer?.let { player ->
            player.pause()
            position = player.currentPosition
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i("lifeCylce", "onStop")
        mediaPlayer?.stop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("lifeCylce", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("lifeCylce", "onDestroy")
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("postion", position)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        position = savedInstanceState.getInt("position")
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(name) }
    TextField(
        value = text,
        onValueChange = {text=it},
        modifier = modifier
    )

    LaunchedEffect(Unit) {
        Log.i("Cryz", "Launched efected")
    }
    DisposableEffect(LocalLifecycleOwner.current) {
        Log.i("Cryz", "Disposable efected")
        onDispose {
            Log.i("Cryz", "onDispose")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LifeCycleJCTheme {
        MainView(Modifier.padding(top = 24.dp), {}, {})
    }
}