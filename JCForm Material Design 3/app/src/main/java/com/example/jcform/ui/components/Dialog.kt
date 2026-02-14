package com.example.jcform.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.jcform.R

@Composable
fun CrDialogInfo(
    info: String,
    confirmRes: Int = R.string.dialog_ok,
    onDismissRequest: (Boolean) -> Unit,
    titleRes: Int){
    AlertDialog(
        title = {
            Text(stringResource(titleRes))
        },
        onDismissRequest = { onDismissRequest(false) },
        text = { Text(info) },
        confirmButton = {
            TextButton(
                onClick = {onDismissRequest(true)}
            ) {
                Text(stringResource(confirmRes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {onDismissRequest(false)}
            ) {
                Text(stringResource(R.string.dialog_cancel))
            }
        }
    )
}