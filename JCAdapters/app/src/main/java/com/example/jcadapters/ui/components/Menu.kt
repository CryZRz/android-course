package com.example.jcadapters.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcadapters.ui.theme.JCAdaptersTheme

@Preview
@Composable
private fun LocalPreview(){
    JCAdaptersTheme {
        SpinnerCr()
    }
}

@Composable
fun SpinnerCr(modifier: Modifier = Modifier, label: String = "", items: List<String> = listOf()){
    var selectedText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {selectedText = it},
            enabled = false,
            label = {
                Text(label)
            },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            },
            modifier = modifier.clickable{isExpanded = true},
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = TextFieldDefaults.colors().unfocusedIndicatorColor,
                disabledTextColor = TextFieldDefaults.colors().unfocusedTextColor,
                disabledLabelColor = TextFieldDefaults.colors().unfocusedLabelColor,
                disabledTrailingIconColor = TextFieldDefaults.colors().unfocusedTrailingIconColor
            )
            )
        DropdownMenu(isExpanded, onDismissRequest = {isExpanded = false}) {
            items.forEach {
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        selectedText = it
                    },
                    text = {
                        Text(it)
                })
            }
        }
    }
}