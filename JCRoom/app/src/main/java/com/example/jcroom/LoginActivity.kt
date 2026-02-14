package com.example.jcroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.lifecycleScope
import com.example.jcroom.room.LocalDatabase
import com.example.jcroom.room.RoomApp
import com.example.jcroom.room.entities.User
import com.example.jcroom.ui.theme.JCRoomTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : ComponentActivity() {

    private val db: LocalDatabase by lazy { LocalDatabase() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCRoomTheme {

                var inProgress by remember { mutableStateOf(false) }


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginView(
                        Modifier.padding(innerPadding),
                        inProgress = inProgress,
                        onLogin = { email, pin ->
                            login(email, pin)
                        },
                        onGoRegister = { email, pin ->
                            launchRegisterMain(email, pin)
                        }
                    )
                }
            }
        }
    }

    private fun login(email: String, pin: String){
        lifecycleScope.launch {
            db.login(email, pin.toIntOrNull() ?: 0){ user ->
                if (user != null){
                    RoomApp.auth = user
                    launchMain()
                }else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@LoginActivity, R.string.login_error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun launchMain(){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun launchRegisterMain(email: String, pin: String){
        val intent = Intent(this, RegisterActivity::class.java)
        val args = Bundle()
        args.putString("email", email)
        args.putString("pin", pin)
        intent.putExtras(args)
        startActivity(intent)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    JCRoomTheme {

    }
}