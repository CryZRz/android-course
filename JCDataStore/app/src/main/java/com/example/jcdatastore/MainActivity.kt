package com.example.jcdatastore

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.jcdatastore.ui.components.CrDialogInput
import com.example.jcdatastore.ui.theme.JCDataStoreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val dataStoreSource: DataStoreSource by lazy { DataStoreSource(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCDataStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var openDialog by remember { mutableStateOf(false) }
                    var usernameValue by remember { mutableStateOf("") }
                    var countries by remember { mutableStateOf(listOf<String>()) }

                    MainView(
                        Modifier.padding(innerPadding),
                        usernameValue,
                        countries,
                        onAddCountry = { country ->
                            //binding.progressBar.visibility = View.VISIBLE
                            lifecycleScope.launch {
                                addCountry(country){ isSucces ->
                                    if (isSucces){
                                        refreshAdapter{ list ->
                                            countries = list
                                        }
                                    }else{

                                    }
                                }
                            }
                        }
                        )

                    if (openDialog){
                        CrDialogInput(
                            onConfirm = { username ->
                                if (username.isBlank()){
                                    Toast.makeText(this, R.string.register_invalid, Toast.LENGTH_SHORT).show()
                                }else{
                                    lifecycleScope.launch {
                                        dataStoreSource.saveUsername(username)
                                        Toast.makeText(this@MainActivity, R.string.register_success, Toast.LENGTH_SHORT).show()
                                    }
                                    usernameValue = username
                                    openDialog = false
                                }
                            },
                            onDismissRequest = {}
                        )

                        val lifecycleObserver = LifecycleEventObserver{ _, event ->
                            when(event){
                                Lifecycle.Event.ON_START -> {
                                    lifecycleScope.launch {
                                        val isFirstTime = dataStoreSource.getFirstTime()
                                        if (isFirstTime){
                                            openDialog = true
                                        }else{
                                            val username = dataStoreSource.getUsername()
                                            usernameValue = username
                                            refreshAdapter{ list ->
                                                countries = list
                                            }
                                        }
                                    }
                                }
                                else -> {}
                            }
                        }

                        val lifecycle = LocalLifecycleOwner.current.lifecycle
                        DisposableEffect(lifecycle) {
                            lifecycle.addObserver(lifecycleObserver)
                            onDispose {
                                lifecycle.removeObserver(lifecycleObserver)
                            }
                        }
                    }

                }
            }
        }
    }

    private suspend fun addCountry(country: String, onFinished: (Boolean) -> Unit) {
        try {
            dataStoreSource.addCountry(country)
            onFinished(true)
            Log.i("CursosANTAG", "addCountry: $country")
        } catch (e: Exception) {
            Toast.makeText(this, R.string.add_country_error, Toast.LENGTH_SHORT).show()
            onFinished(false)
            Log.i("CursosANTAG", "Fail: ${e.message}")
        }
    }

    private fun refreshAdapter(onResult: (List<String>) -> Unit){
        lifecycleScope.launch {
            val countries = dataStoreSource.getCountries()
            onResult(countries)
        }
        try {
            //binding.progressBar.visibility = View.VISIBLE

        }finally {
            //binding.progressBar.visibility = View.INVISIBLE
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LocalPreview() {
    JCDataStoreTheme {
       MainPreview()
    }
}