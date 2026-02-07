package com.example.corutinas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.corutinas.ui.theme.CorutinasTheme

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    CorutinasTheme {
        MainView(Modifier.padding(top = 24.dp), false, 0, {})
    }
}

@Composable
fun MainView(modifier: Modifier, inProcess: Boolean, result: Long, onClick: (Long) -> Unit){
    var inProgressValue by remember { mutableStateOf(false) }
    var resultValue by remember { mutableStateOf("") }
    var numberValue by remember { mutableStateOf("") }

    inProgressValue = inProcess
    resultValue = "Fibonacci $result"

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.common_padding_min)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cargando otro proceso ...")
        CircularProgressIndicator(
            Modifier.padding(vertical = dimensionResource(R.dimen.common_padding_default)),
            trackColor = Color.Red
        )
        OutlinedTextField(
            value = numberValue,
            onValueChange = {numberValue = it},
            modifier = modifier,
            label = {
                Text("Insert a number")
            }
        )
        Button(onClick = {
            onClick(numberValue.toLong())
        }) {
            Text("Calcular")
        }
        if (!inProgressValue) Text(resultValue, modifier)
        if (inProgressValue) CircularProgressIndicator()
    }
}