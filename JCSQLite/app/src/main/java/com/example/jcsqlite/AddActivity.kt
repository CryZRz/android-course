package com.example.jcsqlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcsqlite.sqlite.DatabaseHelper
import com.example.jcsqlite.ui.theme.JCSQLiteTheme
import com.example.xmlsqlite.sqlite.Constants
import kotlinx.coroutines.launch

class AddActivity : ComponentActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCSQLiteTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { innerPadding ->
                    AddView(Modifier.padding(innerPadding)) { name, isFav ->
                        checkAndSave(name, isFav){
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    getString(R.string.add_error_save),
                                    duration = SnackbarDuration.Long
                                    )
                            }
                        }
                    }
                }
            }
        }

        setupDatabase()
    }

    private fun setupDatabase(){
        db = DatabaseHelper(this)
    }

    private fun checkAndSave(name: String, isFav: Boolean, onError: () -> Unit) {
        if (name.isNotEmpty()){
            val park = Park(
                name = name,
                isFav = isFav
            )
            savePark(park){ isSuccess ->
                if (isSuccess){
                    finish()
                }else{
                    onError()
                }
            }
        }
    }

    private fun savePark(park: Park, onFinished: (Boolean) -> Unit){
        park.id = db.insertPark(park)

        onFinished(park.id != Constants.ERROR_ID)
        finish()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JCSQLiteTheme {
        AddPreview()
    }
}