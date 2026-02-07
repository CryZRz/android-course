package com.example.xmlintents

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jcintents.LaunchIntent
import com.example.xmlintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val launchIntent: LaunchIntent by lazy { LaunchIntent(this) }
    val galleryResul = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            println("cryz ${it.data?.data}")
        }
    }

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

        setupButtons()
    }

    private fun setupButtons(){
        with(binding){
            btnShare.setOnClickListener { launchIntent.shareText() }
            btnAlarm.setOnClickListener {launchIntent.setAlarm()}
            btnCalendar.setOnClickListener {launchIntent.setCalendar()}
            btnCamera.setOnClickListener {launchIntent.openCamera()}
            btnFiles.setOnClickListener {launchIntent.openFiles()}
            btnSettings.setOnClickListener {launchIntent.openSettings()}
            btnContacts.setOnClickListener {launchIntent.addContact()}
            btnDial.setOnClickListener {launchIntent.dialPhone()}
            btnEmail.setOnClickListener {launchIntent.sendEmail()}
            btnSms.setOnClickListener {launchIntent.sendSms()}
            btnMaps.setOnClickListener {launchIntent.showMap()}
            btnMusic.setOnClickListener {launchIntent.searchMusic()}
            btnSearch.setOnClickListener {launchIntent.search()}
            btnWeb.setOnClickListener {launchIntent.openWeb()}
        }
    }
}