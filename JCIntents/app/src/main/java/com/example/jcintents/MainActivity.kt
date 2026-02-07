package com.example.jcintents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcintents.ui.theme.JCIntentsTheme

class MainActivity : ComponentActivity() {
    private val launchIntent: LaunchIntent by lazy { LaunchIntent(this) }
    val galleryResul = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            println("cryz ${it.data?.data}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCIntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(modifier = Modifier.padding(innerPadding)) { actionIntent ->
                        setupButtons(actionIntent)
                    }
                }
            }
        }
    }

    private fun setupButtons(actionIntent: ActionIntent) {
        when(actionIntent){
            ActionIntent.SHARE -> launchIntent.shareText()
            ActionIntent.ALARM -> launchIntent.setAlarm()
            ActionIntent.CALENDAR -> launchIntent.setCalendar()
            ActionIntent.CAMERA -> launchIntent.openCamera()
            ActionIntent.FILES -> launchIntent.openFiles()
            ActionIntent.SETTINGS -> launchIntent.openSettings()
            ActionIntent.CONTACTS -> launchIntent.addContact()
            ActionIntent.CALL -> launchIntent.dialPhone()
            ActionIntent.EMAIL -> launchIntent.sendEmail()
            ActionIntent.SMS -> launchIntent.sendSms()
            ActionIntent.MAPS -> launchIntent.showMap()
            ActionIntent.MUSIC -> launchIntent.searchMusic()
            ActionIntent.SEARCH -> launchIntent.search()
            ActionIntent.WEB -> launchIntent.openWeb()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LocalPreview() {
    JCIntentsTheme {
        MainPreview()
    }
}