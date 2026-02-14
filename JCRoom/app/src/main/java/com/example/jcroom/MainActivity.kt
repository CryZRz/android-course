package com.example.jcroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.jcroom.room.LocalDatabase
import com.example.jcroom.room.RoomApp
import com.example.jcroom.room.entities.Insect
import com.example.jcroom.ui.components.CrDialogInfo
import com.example.jcroom.ui.theme.JCRoomTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private val db: LocalDatabase by lazy { LocalDatabase() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCRoomTheme {

                var insects by remember { mutableStateOf(listOf<Insect>()) }
                var inProgress by remember { mutableStateOf(true) }

                var expandedMenu by remember { mutableStateOf(false) }
                var openDialog by remember { mutableStateOf(false) }
                var selectedInsect: Insect? = null

                var onlyMine = true

                val refresh = {
                    refreshData(onlyMine) { result ->
                        inProgress = false
                        insects = result
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            ),
                            title = {Text(stringResource(R.string.app_name))},
                            actions = {
                                Box{
                                    IconButton(
                                        onClick = {
                                            expandedMenu = true
                                        }
                                    ) {
                                        Icon(Icons.Default.MoreVert, contentDescription = null)
                                    }
                                    DropdownMenu(
                                        expanded = expandedMenu,
                                        onDismissRequest = {expandedMenu = false}
                                    ) {
                                        DropdownMenuItem(
                                            text = {Text(stringResource(R.string.main_menu_import))},
                                            onClick = {
                                                expandedMenu = false
                                                importDefault{
                                                    refresh()
                                                }
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = {Text(stringResource(R.string.main_menu_users))},
                                            onClick = {
                                                expandedMenu = false
                                                lauchUsers()
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = {Text(stringResource(R.string.main_delete_import))},
                                            onClick = {
                                                expandedMenu = false
                                                deleteInsectsDefault(UID_FAKE){}
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = {
                                                Text(stringResource(R.string.main_menu_delete_mydata), color = Color.Red)
                                                   },
                                            onClick = {
                                                expandedMenu = false
                                                deleteAllMyData(){

                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            onClick = {
                                launchAdd()
                            },
                            icon = { Icon(Icons.Default.Add, contentDescription = null) },
                            text = {Text(stringResource(R.string.main_efab_add))}
                        )
                    }
                ) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        insects = insects,
                        inProgress = inProgress,
                        onFilter = {
                            onlyMine = it
                            refresh()
                        },
                        initFilter = onlyMine,
                        onLongClick = { insect ->
                            selectedInsect = insect
                            openDialog = true
                        }
                    )

                    if (openDialog){
                        selectedInsect?.let { insect ->
                            CrDialogInfo(
                                info = getString(R.string.dialog_msg_warning),
                                titleRes = R.string.dialog_delete_title,
                                confirmRes = R.string.dialog_delete_confirm,
                                onDismissRequest = { delete ->
                                    if (delete){
                                        deleteInsect(insect){
                                            insects = insects.toMutableList().also { it.remove(insect) }
                                        }
                                    }
                                    openDialog = false
                                }
                            )
                        }
                    }
                }

                val lifecycleObserver = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME -> {
                            lifecycleScope.launch {
                                refresh()
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

    private fun deleteAllMyData(onRemove: () -> Unit) {}

    private fun deleteInsectsDefault(uidFake: Long, onRemove: () -> Unit) {

    }

    private fun lauchUsers() {
        TODO("Not yet implemented")
    }

    private fun launchAdd() {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    private fun importDefault(onResult: () -> Unit) {
        lifecycleScope.launch {
            db.addInsects(getAllInsectsDefault()){ success ->
                onResult()
                val msg = if (success) {
                    R.string.main_import_success
                }else{
                    R.string.main_import_error
                }

                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun deleteInsect(insect: Insect, onRemove: () -> Unit){
        if (insect.uid == RoomApp.auth.id){
            lifecycleScope.launch {
                db.deleteInsect(insect){ isDeleted, msg ->
                    if (isDeleted){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }else{
            Toast.makeText(this@MainActivity, "Solo puedes eliminar tus insectos", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(
            this,
            getString(R.string.main_message_welcome, RoomApp.auth.name),
            Toast.LENGTH_SHORT).show()
    }

    private fun refreshData(onlyMine: Boolean, onResult: (List<Insect>) -> Unit){
        lifecycleScope.launch {
//            db.getAllInsects { result ->
//                onResult(result)
//            }
//            db.getMyInsects { result ->
//                onResult(result)
//            }

            db.getInsectsWithFilter(onlyMine){ result ->
                onResult(result)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JCRoomTheme {
        MainViewPreview()
    }
}