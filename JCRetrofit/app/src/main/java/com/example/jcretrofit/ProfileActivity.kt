package com.example.jcretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.jcretrofit.entities.SingleUserResponse
import com.example.jcretrofit.retrofit.RemoteDataBase
import com.example.jcretrofit.ui.theme.JCRetrofitTheme
import kotlinx.coroutines.launch

class ProfileActivity : ComponentActivity() {

    private val db: RemoteDataBase by lazy { RemoteDataBase(lifecycleScope, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCRetrofitTheme {

                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                var inProgress by remember { mutableStateOf(false) }
                var userResponse by remember { mutableStateOf<SingleUserResponse?>(null) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { innerPadding ->
                    ProfileView(
                        Modifier.padding(innerPadding),
                        response = userResponse,
                        inProgress = inProgress
                    )
                }

                val lifecycleObserver = LifecycleEventObserver{ _, event ->
                    when(event){
                        Lifecycle.Event.ON_START -> {
                            lifecycleScope.launch {
                                inProgress = true
                                db.getSingleUser{
                                    db.getSingleUser { response ->
                                        if (response != null){
                                            userResponse = response
                                        }else{
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = getString(R.string.profile_error_user_not_found)
                                                )
                                            }
                                        }
                                    }
                                }
                                inProgress = false
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

@Preview(showBackground = true)
@Composable
private fun LocalProfilePreview() {
    JCRetrofitTheme {
        ProfilePreview()
    }
}