package com.example.jcintents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material.icons.outlined.Web
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcintents.ui.theme.JCIntentsTheme

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    JCIntentsTheme {
        MainView(Modifier.padding(top = 24.dp), {})
    }
}

@Composable
fun MainView(modifier: Modifier, onClickHandler: (ActionIntent) -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp)
    ) {
        Button(onClick = {onClickHandler(ActionIntent.SHARE)}) {
            Icon(Icons.Outlined.Share, contentDescription = null)
            Text(stringResource(R.string.intent_share))
        }
        Button(onClick = {onClickHandler(ActionIntent.ALARM)}) {
            Icon(Icons.Outlined.Alarm, contentDescription = null)
            Text(stringResource(R.string.intent_alarm))
        }
        Button(onClick = {onClickHandler(ActionIntent.CALENDAR)}) {
            Icon(Icons.Outlined.CalendarMonth, contentDescription = null)
            Text(stringResource(R.string.intent_celendar))
        }
        Button(onClick = {onClickHandler(ActionIntent.CAMERA)}) {
            Icon(Icons.Outlined.Camera, contentDescription = null)
            Text(stringResource(R.string.intent_camera))
        }
        Button(onClick = {onClickHandler(ActionIntent.FILES)}) {
            Icon(Icons.Outlined.Folder, contentDescription = null)
            Text(stringResource(R.string.intent_files))
        }
        Button(onClick = {onClickHandler(ActionIntent.SETTINGS)}) {
            Icon(Icons.Outlined.Settings, contentDescription = null)
            Text(stringResource(R.string.intent_setings))
        }
        Button(onClick = {onClickHandler(ActionIntent.CONTACTS)}) {
            Icon(Icons.Outlined.Contacts, contentDescription = null)
            Text(stringResource(R.string.intent_contacts))
        }
        Button(onClick = {onClickHandler(ActionIntent.CALL)}) {
            Icon(Icons.Outlined.Phone, contentDescription = null)
            Text(stringResource(R.string.intent_call))
        }
        Button(onClick = {onClickHandler(ActionIntent.EMAIL)}) {
            Icon(Icons.Outlined.Email, contentDescription = null)
            Text(stringResource(R.string.intent_email))
        }
        Button(onClick = {onClickHandler(ActionIntent.SMS)}) {
            Icon(Icons.Outlined.Sms, contentDescription = null)
            Text(stringResource(R.string.intent_sms))
        }
        Button(onClick = {onClickHandler(ActionIntent.MAPS)}) {
            Icon(Icons.Outlined.Map, contentDescription = null)
            Text(stringResource(R.string.intent_maps))
        }
        Button(onClick = {onClickHandler(ActionIntent.MUSIC)}) {
            Icon(Icons.Outlined.MusicNote, contentDescription = null)
            Text(stringResource(R.string.intent_music))
        }
        Button(onClick = {onClickHandler(ActionIntent.SEARCH)}) {
            Icon(Icons.Outlined.Search, contentDescription = null)
            Text(stringResource(R.string.intent_search))
        }
        Button(onClick = {onClickHandler(ActionIntent.WEB)}) {
            Icon(Icons.Outlined.Web, contentDescription = null)
            Text(stringResource(R.string.intent_web))
        }

    }
}