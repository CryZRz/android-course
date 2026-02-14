package com.example.jcroom.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.jcroom.ui.theme.JCRoomTheme
import com.example.jcroom.R

@Preview
@Composable
private fun CrTextFieldPreview(){
    JCRoomTheme {
        CrTextField(
            modifier = Modifier,
            labelRes = R.string.hint_name,
            iconRes = Icons.Default.Person,
            maxLengthRes = R.integer.name_max_length,
            onValueChange = {}
        )
    }
}
@Composable
fun CrTextField(
    modifier: Modifier = Modifier,
    labelRes: Int,
    iconRes: ImageVector,
    maxLengthRes: Int? = null,
    onValueChange: (String) -> Unit,
    minValue: Int = 0,
    errorRes: Int = R.string.supporting_required,
    keyboardOptions: KeyboardOptions? = null,
    isCleaned: Boolean = false,
    singleLine: Boolean = true,
    isRequired: Boolean = true,
    paddingTop: Dp = dimensionResource(R.dimen.common_padding_default),
    initText: String = ""
){
    var textValue by remember { mutableStateOf(initText) }
    var isError by remember { mutableStateOf(false) }
    val maxLength = if (maxLengthRes == null) null else integerResource(maxLengthRes)

    if (isCleaned){
        textValue = ""
    }

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            if (maxLength == null){
                textValue = it
            }else{
                if (it.length <= maxLength ) textValue = it
            }

            isError = it.trim().isEmpty()

            if (minValue > 0){
                isError = (textValue.toIntOrNull() ?: 0) <= minValue
            }

            onValueChange(textValue)
        },
        isError = isError,
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth()
            .padding(top = paddingTop),
        label = {
            Text(
                stringResource(labelRes),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
        },
        leadingIcon = {
            Icon(imageVector = iconRes, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardOptions?.capitalization ?: KeyboardCapitalization.Sentences,
            keyboardType = keyboardOptions?.keyboardType ?: KeyboardType.Text,
            imeAction = if (
                keyboardOptions == null || keyboardOptions.imeAction == ImeAction.Default
                )
                ImeAction.Next
                else keyboardOptions.imeAction
        ),
        supportingText = {
            Row {
                if(isRequired) Text(if (isError) stringResource(errorRes) else stringResource(R.string.supporting_required))
                Spacer(Modifier.weight(1f))
                if (maxLength != null && minValue == 0) Text("${textValue.length}/$maxLength")
            }
        }
    )
}
