package com.example.jcdatastore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcdatastore.ui.theme.JCDataStoreTheme
import com.example.jcdatastore.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    JCDataStoreTheme {
        MainView(Modifier.padding(top = 24.dp,), "Christian", listOf("Francia", "Spain", "Grecia"), {})
    }
}


@Composable
fun MainView(
    modifier: Modifier,
    username: String,
    countries: List<String>,
    onAddCountry: (String) -> Unit
    ){
    val appName = stringResource(R.string.app_name)
    val welcomeTitle = LocalContext.current.getString(R.string.main_title, username)
    val title = if (username.isEmpty()) appName else welcomeTitle

    var countryValue by remember { mutableStateOf("") }

    Column(
        modifier.padding(
            horizontal = dimensionResource(R.dimen.common_padding_large),
            vertical = dimensionResource(R.dimen.common_padding_default)),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = Typography.headlineMedium)
        OutlinedTextField(
            value = countryValue,
            onValueChange = {countryValue = it},
            label = {
                Text(stringResource(R.string.hint_country))
            },
            modifier = Modifier.fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.common_padding_default))
            )

        Button(
            onClick = {
                onAddCountry(countryValue)
            },
            modifier = Modifier.padding(top = dimensionResource(R.dimen.common_padding_min))
            ) {
            Icon(Icons.Default.LocationOn, contentDescription = null)
            Text(stringResource(R.string.btn_add_country))
        }

        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.common_padding_micro))
        )
        LazyColumn {
            items(countries.size){ index ->
                Text(
                    countries[index],
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.common_padding_min)),
                    style = Typography.titleLarge
                    )
            }
        }
    }
}