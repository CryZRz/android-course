package com.example.jcretrofit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.jcretrofit.retrofit.RemoteDataBase
import com.example.jcretrofit.ui.theme.JCRetrofitTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val db: RemoteDataBase by lazy { RemoteDataBase(lifecycleScope, this) }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCRetrofitTheme {

                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                var inProgress by remember { mutableStateOf(false) }

                val showMessage = { msg: String ->
                    scope.launch {
                        inProgress = false
                        snackbarHostState.showSnackbar(message = msg)
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        inProgress = inProgress,
                        onGoUser = {launchUsers()},
                        onClick = { user, isLogin ->
                            inProgress = true
                            if (isLogin){
                                db.login(
                                    user,
                                    onLogin = {
                                        launchProfile()
                                        inProgress = false
                                    },
                                    onError = { error ->
                                        showMessage(error)
                                    }
                                )
                            }else{
                                db.register(
                                    user,
                                    onRegister = {
                                        showMessage(it)
                                    },
                                    onError = { error ->
                                        showMessage(error)
                                    }
                                )
                            }
                        }
                    )
                }
            }
        }
    }

    private fun launchUsers() {
        startActivity(Intent(this, UsersActivity::class.java))
    }

    private fun launchProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
}

@Preview(showBackground = true)
@Composable
private fun LocalMainPreview() {
    JCRetrofitTheme {
        Box(Modifier)
    }
}