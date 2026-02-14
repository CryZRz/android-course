package com.example.jcsqlite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcsqlite.ui.components.CrCheckBox
import com.example.jcsqlite.ui.theme.JCSQLiteTheme

@Preview(showBackground = true)
@Composable
fun AddPreview(){
    JCSQLiteTheme {
        AddView(Modifier.padding(top = 24.dp), onSave = {val1, val2 -> })
    }
}

@Composable
fun AddView(modifier: Modifier, onSave: (String, Boolean) -> Unit){

    var nameValue by remember { mutableStateOf("") }
    var isFavorite by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    val maxLength = integerResource(R.integer.name_max_length)

    Column(
        Modifier.padding(dimensionResource(R.dimen.common_padding_default))
    ) {
        OutlinedTextField(
            value = nameValue,
            onValueChange = {
                if (nameValue.length < maxLength){
                    nameValue = it
                }
                isError = nameValue.isEmpty()
            },
            label = {
                Text(stringResource(R.string.add_hint_name))
            },
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Row {
                    if (isError) Text(stringResource(R.string.add_field_required))
                    Text(
                        "${nameValue.length}/$maxLength",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*
            Checkbox(
                checked = isFavorite,
                onCheckedChange = {isFavorite = it}
            )
             */
            CrCheckBox(
                checked = isFavorite,
                onCheckedChange = {isFavorite = it}
            )
            Text(
                stringResource(R.string.add_db_favorite),
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.common_padding_default))
                )
        }

        Button(
            onClick = {onSave(nameValue, isFavorite)},
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.common_padding_default))
        ) {
            Icon(painterResource(R.drawable.ic_save), contentDescription = null)
            Text(
                stringResource(R.string.add_btn_save),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.common_padding_min))
            )
        }
    }
}