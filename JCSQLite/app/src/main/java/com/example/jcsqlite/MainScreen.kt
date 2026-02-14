package com.example.jcsqlite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcsqlite.ui.theme.JCSQLiteTheme
import com.example.jcsqlite.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    JCSQLiteTheme {
        MainView(Modifier.padding(top = 24.dp), parksPreview, {}, {})
    }
}

@Composable
fun MainView(
    modifier: Modifier,
    parks: List<Park>,
    onClick: (Park) -> Unit,
    onLongClick: (Park) -> Unit
){
    Column(
        modifier = modifier.
        padding(dimensionResource(R.dimen.common_padding_default))
    ){
        Text(
            stringResource(R.string.app_name),
            style = Typography.displaySmall
            )
        LazyColumn {
            items(parks.size){ index ->
                val park = parks[index]
                ItemParkView(
                    park,
                    onClick = {onClick(it)},
                    onLongClick = {onLongClick(it)}
                    )
            }
        }
    }
}