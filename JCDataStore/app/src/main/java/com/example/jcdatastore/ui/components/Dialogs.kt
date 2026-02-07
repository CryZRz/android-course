package com.example.jcdatastore.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.jcdatastore.ui.theme.JCDataStoreTheme
import com.example.jcdatastore.R

@Preview
@Composable
private fun LocalPreview(){
    JCDataStoreTheme {
        CrDialogInput(){}
    }
}
@Composable
fun CrDialogInput(
    onDismissRequest: () -> Unit = {},
    onConfirm: (String) -> Unit
){
    var textValue by remember { mutableStateOf("") }
    Dialog(
        onDismissRequest = {onDismissRequest()}
    ) {
        Card {
            Column(
                Modifier.padding(dimensionResource(R.dimen.common_padding_default))
            ) {
                Text(stringResource(R.string.dialog_title))

                val maxLength = 20
                OutlinedTextField(
                    value = textValue,
                    onValueChange = {textValue = it},
                    label = {
                        Text(stringResource(R.string.hint_username))
                    },
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.common_padding_default)),
                    supportingText = {
                        Row {
                            Text(stringResource(R.string.helper_required))
                            Spacer(Modifier.weight(1f))
                            Text("${textValue.length}/$maxLength")
                        }
                    }
                )

                Row {
                    Spacer(Modifier.weight(1f))

                    TextButton(
                        onClick = {onConfirm(textValue)}
                    ) {
                        Text(stringResource(R.string.dialog_confirm))
                    }
                }
            }
        }
    }
}