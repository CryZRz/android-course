package com.example.corutinas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.corutinas.ui.theme.CorutinasTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

private lateinit var fibJob: Job
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CorutinasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var result by remember{ mutableStateOf(0L) }
                    var inProgress by remember { mutableStateOf(false) }

                    MainView(
                        Modifier.fillMaxWidth().padding(innerPadding),
                        inProgress,
                        result
                        ){ num ->
                        inProgress = true
                        startFib(num){ response ->
                            result = response
                            inProgress = false
                        }
                    }
                }
            }
        }
    }

    /*
    * Dispatchers
    * Main es para actualizar la ui
    * IO: Operaciones E/S
    * Default Alto consumo del CPU
    * */
    private fun startFib(number: Long, onResult: (Long) -> Unit){
        fibJob = CoroutineScope(Job()).launch(Dispatchers.Default) {
            val fib = fibonacci(number)

            withContext(Dispatchers.Main){
                onResult(fib)
            }

        }

    }

    private fun fibonacci(n: Long): Long{
        if (fibJob.isCancelled) Log.i("Cryz", "fibonacci cancelado")
        return if (n <= 1) n
        else fibonacci(n - 1) + fibonacci(n - 2)
    }

    override fun onDestroy() {
        if (fibJob.isActive) fibJob.cancel()
        super.onDestroy()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CorutinasTheme {
        MainPreview()
    }
}