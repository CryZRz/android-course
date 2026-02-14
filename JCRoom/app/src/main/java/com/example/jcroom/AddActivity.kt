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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.jcroom.room.LocalDatabase
import com.example.jcroom.room.entities.Insect
import com.example.jcroom.ui.theme.JCRoomTheme
import kotlinx.coroutines.launch

class AddActivity : ComponentActivity() {

    private val db: LocalDatabase by lazy { LocalDatabase() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCRoomTheme {

                var inProgress by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddView(
                        Modifier.padding(innerPadding),
                        onSave = { insect ->
                            inProgress = true
                            saveInsect(insect){
                                inProgress = false
                            }
                        },
                        inProgress = inProgress
                    )
                }
            }
        }
    }

    private fun saveInsect(insect: Insect, onError: () -> Unit) {
        lifecycleScope.launch {
            db.addInsect(insect){ success, msgRes ->
                if (success){
                    finish()
                }else{
                    onError()
                    Toast.makeText(this@AddActivity, msgRes, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JCRoomTheme {
        //AddView()
    }
}