package com.example.jcretrofit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcretrofit.entities.UserInfo
import com.example.jcretrofit.ui.components.ProgressFullScreen
import com.example.jcretrofit.ui.theme.JCRetrofitTheme
import com.example.jcretrofit.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    JCRetrofitTheme {
        MainView(Modifier.padding(top = 24.dp), false, {}, {_,_ ->})
    }
}

@Composable
fun MainView(
    modifier: Modifier,
    inProgress: Boolean = false,
    onGoUser: () -> Unit,
    onClick: (UserInfo, Boolean) -> Unit
    ){
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
    ){
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(R.string.main_login_title),
                style = Typography.headlineSmall
            )

            OutlinedTextField(
                value = emailValue,
                onValueChange = {emailValue = it},
                modifier = Modifier.padding(top = dimensionResource(R.dimen.common_padding_min)),
                label = {
                    Text(stringResource(R.string.main_hint_email))
                }
            )

            OutlinedTextField(
                value = passwordValue,
                onValueChange = {passwordValue = it},
                modifier = Modifier.padding(top = dimensionResource(R.dimen.common_padding_min)),
                label = {
                    Text(stringResource(R.string.main_hint_password))
                }
            )

            Row(
                Modifier.padding(vertical = dimensionResource(R.dimen.common_padding_default)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.common_padding_min))
                ) {
                Text(if (isLogin) stringResource(R.string.main_switch_login) else stringResource(R.string.main_switch_register))

                Switch(
                    checked = isLogin,
                    onCheckedChange = {isLogin = it},
                    thumbContent = {
                        Icon(
                            Icons.Default.Repeat,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                            )
                    }
                )
            }

            Button(
                onClick = {
                    onClick(UserInfo(emailValue, passwordValue), isLogin)
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.Login,
                    contentDescription = null,
                    modifier = Modifier.padding(end = dimensionResource(R.dimen.common_padding_min))
                    )
                Text(stringResource(R.string.main_btn_login))
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {onGoUser()}
            ) {
                Icon(
                    Icons.Default.People,
                    contentDescription = null,
                    modifier = Modifier.padding(end = dimensionResource(R.dimen.common_padding_min))
                )
                Text(stringResource(R.string.users_title))
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