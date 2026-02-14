package com.example.jcretrofit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcretrofit.entities.Data
import com.example.jcretrofit.entities.SingleUserResponse
import com.example.jcretrofit.ui.components.ProgressFullScreen
import com.example.jcretrofit.ui.theme.JCRetrofitTheme
import com.example.jcretrofit.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun UsersPreview(){
    JCRetrofitTheme {
        UsersView(Modifier.padding(top = 24.dp), listOf(),false)
    }
}

@Composable
fun UsersView(modifier: Modifier, users: List<Data>, inProgress: Boolean = false){
    Box(modifier){

        Column(
            modifier.padding(horizontal = dimensionResource(R.dimen.common_padding_default)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.users_title),
                style = Typography.headlineSmall
            )

            LazyColumn {
                items(users.size){ index ->
                    val user = users[index]
                    ItemUserView(user)
                }
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