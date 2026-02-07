package com.example.jcform

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcform.ui.components.CrDialogInfo
import com.example.jcform.ui.theme.JCFormTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCFormTheme {

                val snackBarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                var openDialog by remember{ mutableStateOf(false) }
                var userFilled: User? = null
                var cleanForm by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost  = {
                        SnackbarHost(snackBarHostState)
                    }
                ) { innerPadding ->
                    MainView(
                        modifier =  Modifier.padding(innerPadding),
                        onSave = { user ->
                        Log.i("Chris", "onCreate $user")
                        userFilled = user
                        openDialog = true
                    },
                        isClean = cleanForm,
                        onCleaned = {cleanForm = false},
                        onError = { error ->
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = error,
                                    actionLabel = getString(R.string.dialog_ok),
                                    duration = SnackbarDuration.Long
                                )
                            }
                        }
                    )

                    if (openDialog){
                        userFilled?.let { user ->
                            CrDialogInfo(
                                info = userFormater(user),
                                titleRes = R.string.dialog_title,
                                confirmRes = R.string.dialog_clean,
                                onDismissRequest = { clean ->
                                    cleanForm = clean
                                    openDialog = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    JCFormTheme {
        MainView(Modifier, {}, {}, true, {})
    }
}