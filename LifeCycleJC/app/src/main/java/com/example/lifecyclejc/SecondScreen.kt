package com.example.lifecyclejc

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

private var position: Long = 0
@Composable
fun ExoPlayerView(modifier: Modifier) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()

    val playerView = remember {
        PlayerView(context).apply {
            player = exoPlayer
        }
    }

    val lifecyclkeObserver = RememberLifecycleObserver(playerView, context)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecyclkeObserver) {
        lifecycle.addObserver(lifecyclkeObserver)
        onDispose {
            playerView.player?.release()
            lifecycle.removeObserver(lifecyclkeObserver)
        }
    }

    AndroidView(
        factory = {playerView},
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun RememberLifecycleObserver(player: PlayerView, context: Context): LifecycleEventObserver = remember(player){
    LifecycleEventObserver{ source, event ->
        when(event){
            Lifecycle.Event.ON_CREATE -> Log.i("ExoplayerLifecycle", "OnCreate")
            Lifecycle.Event.ON_START -> {
                Log.i("ExoplayerLifecycle", "OnStart")
                val audioUri = "android.resource://${context.packageName}/raw/song".toUri()
                val videoUri = "https://www.pexels.com/download/video/35475314/".toUri()
                val mediaItem = MediaItem.fromUri(videoUri)
                player.player?.setMediaItem(mediaItem)
                player.player?.prepare()
                player.player?.repeatMode = Player.REPEAT_MODE_ONE
            }
            Lifecycle.Event.ON_RESUME -> {
                Log.i("ExoplayerLifecycle", "OnResume")
                player.player?.seekTo(position)
                player.player?.play()
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.i("ExoplayerLifecycle", "OnPause")
                player.player?.pause()
                position = player.player?.currentPosition ?: 0
            }
            Lifecycle.Event.ON_STOP -> {
                Log.i("ExoplayerLifecycle", "OnStop")
                player.player?.stop()
            }
            Lifecycle.Event.ON_DESTROY -> {
                Log.i("ExoplayerLifecycle", "OnDestroy")
                //player.player?.release()
            }
            else -> {}
        }
    }
}