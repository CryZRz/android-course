package com.example.lifecicle.second

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.lifecicle.R
import com.example.lifecicle.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var player: ExoPlayer
    private var position: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("Second LifeCycle", "onCreate")
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player
    }

    override fun onStart() {
        super.onStart()
        Log.i("Second lifeCylce", "onStart")
        val audioUri = "android.resource://$packageName/raw/song".toUri()
        val videoUri = "https://www.pexels.com/download/video/35475314/".toUri()
        val mediaItem = MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.repeatMode = Player.REPEAT_MODE_ONE
    }

    override fun onResume() {
        super.onResume()
        Log.i("Second lifeCylce", "onResume")
        player.seekTo(position)
        player.play()
    }

    override fun onPause() {
        super.onPause()
        Log.i("Second lifeCylce", "onPause")
        player.pause()
        position = player.currentPosition
    }

    override fun onStop() {
        super.onStop()
        Log.i("Second lifeCylce", "onStop")
        player.stop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Second lifeCylce", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Second lifeCylce", "onDestroy")
        player.release()
    }
}