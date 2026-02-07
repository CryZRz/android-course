package com.example.primerintento

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import kotlin.math.log

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var tts: TextToSpeech? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tts = TextToSpeech(this, this)

        val text = findViewById<EditText>(R.id.editTextText2).text

        findViewById<Button>(   R.id.button).setOnClickListener {
            if (text.isNotEmpty()){
                Log.i("Cursos ant primer intento", "Hola $text")
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }else{
                tts?.speak("Vous parles espagnol?", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }


    }

    override fun onInit(status: Int) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        if (status == TextToSpeech.SUCCESS){
            tts?.language = Locale("fr")
            findViewById<TextView>(R.id.textView).text = "Text to speech disponible"
        }else{
            findViewById<TextView>(R.id.textView).text = "Text to speech no disponible"
        }

        progressBar.visibility = View.GONE
    }
}