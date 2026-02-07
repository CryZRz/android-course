package com.example.lifecicle

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lifecicle.databinding.ActivityMainBinding
import com.example.lifecicle.second.SecondActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private var positon: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnLaunch.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.btnDialog.setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
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
        mediaPlayer?.seekTo(positon)
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        Log.i("lifeCylce", "onPause")
        //mediaPlayer?.pause()

        mediaPlayer?.let { player ->
            player.pause()
            positon = player.currentPosition
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
        outState.putString("text", binding.editText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.editText.setText(savedInstanceState.getString("texto",""))
    }
}