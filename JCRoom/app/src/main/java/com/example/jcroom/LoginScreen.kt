package com.example.jcroom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.jcroom.ui.components.CrTextField
import com.example.jcroom.ui.components.ProgressFullScreen
import com.example.jcroom.ui.theme.Typography

@Composable
fun LoginView(
    modifier: Modifier,
    inProgress: Boolean,
    onLogin: (String, String) -> Unit,
    onGoRegister: (String, String) -> Unit,
){

    var emailValue by remember { mutableStateOf("") }
    var pinValue by remember { mutableStateOf("") }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier.padding(dimensionResource(R.dimen.common_padding_default)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.login_title),
                style = Typography.headlineMedium
            )

            CrTextField(
                labelRes = R.string.login_hint_email,
                iconRes = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                onValueChange = {emailValue = it}
            )

            CrTextField(
                labelRes = R.string.login_hint_pin,
                iconRes = Icons.Default.Pin,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                onValueChange = {pinValue = it}
            )

            Button(
                onClick = {onLogin(emailValue, pinValue)},
                modifier = modifier.padding(top = dimensionResource(R.dimen.common_padding_default))
            ) {
                Icon(Icons.AutoMirrored.Filled.Login, contentDescription = null)
                Text(
                    stringResource(R.string.login_btn_login),
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.common_padding_min))
                )
            }

            OutlinedButton(
                onClick = {onGoRegister(emailValue, pinValue)},
                modifier = modifier.padding(top = dimensionResource(R.dimen.common_padding_default))
            ) {
                Icon(Icons.Default.AppRegistration, contentDescription = null)
                Text(
                    stringResource(R.string.login_btn_register),
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.common_padding_min))
                )
            }
        }

        AnimatedVisibility(
            visible = inProgress,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ProgressFullScreen()
        }

    }
}