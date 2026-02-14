package com.example.jcroom

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.jcroom.room.LocalDatabase
import com.example.jcroom.room.entities.User
import com.example.jcroom.ui.theme.JCRoomTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : ComponentActivity() {

    private val db: LocalDatabase by lazy { LocalDatabase() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var email = ""
        var pin = ""

        intent.extras?.let { args ->
            email = args.getString("email", "")
            pin = args.getString("pin", "")
        }

        setContent {
            JCRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterView(
                        Modifier.padding(innerPadding),
                        inProgress = false,
                        email = email,
                        pin = pin,
                        onRegister = { user ->
                            regitser(user)
                        },
                    )
                }
            }
        }
    }

    private fun regitser(user: User) {
        lifecycleScope.launch {
            db.registerUser(user){ success, msg ->
                if (success){
                    finish()
                }else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@RegisterActivity, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    JCRoomTheme {
    }
}