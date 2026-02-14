package com.example.jcform

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcform.ui.components.DatePickerModal
import com.example.jcform.ui.theme.JCFormTheme
import com.example.jcform.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    JCFormTheme {
        MainView(Modifier.padding(top = 24.dp), {}, {}, true, {})
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainView(
    modifier: Modifier,
    onSave: (User) -> Unit,
    onCleaned: () -> Unit,
    isClean: Boolean = false,
    onError: (String) -> Unit
){
    val context = LocalContext.current
    val isShowKeyBoard = WindowInsets.isImeVisible

    var nameValue by remember { mutableStateOf("") }
    var surnameValue by remember { mutableStateOf("") }
    var heightValue by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var dateValue by remember { mutableStateOf<Long?>(1L) }
    var notesValue by remember { mutableStateOf("") }
    var isAgree by remember { mutableStateOf(false) }

    val profiles = listOf("Estudiante", "programmeuse")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(profiles[0]) }

    if (isClean){
        dateValue = null
        onOptionSelected(profiles[0])
        isAgree = false
        onCleaned()
    }

    Box(
        modifier.fillMaxWidth()
    ) {
        Card(Modifier.padding(8.dp)) {
            Column(
                Modifier.padding(horizontal = dimensionResource(R.dimen.common_padding_default))
            ) {


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        stringResource(R.string.form_title),
                        modifier = Modifier.weight(1f),
                        style = Typography.titleLarge
                    )

                    val saveAlpha = if (isShowKeyBoard) 1f else 0f

                    Button(
                        onClick = {
                            val errors = foundErrors(context, nameValue, surnameValue, heightValue)
                            if (errors == null){
                                val user = User(
                                    nameValue,
                                    surnameValue,
                                    heightValue.toInt(),
                                    dateValue ?: 0,
                                    selectedOption,
                                    notesValue
                                )
                                onSave(user)
                            }else{
                                onError(errors)
                            }
                        },
                        modifier = Modifier.alpha(saveAlpha),
                        enabled = isAgree
                    ) {
                        Icon(painter = painterResource(R.drawable.ic_check), contentDescription = null)
                    }
                }

                //Name
                CrTextField(
                    labelRes = R.string.hint_name,
                    iconRes = R.drawable.ic_person,
                    maxLengthRes = R.integer.name_max_length,
                    onValueChange = {nameValue = it},
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    isCleaned = isClean
                )
                //Surname
                CrTextField(
                    labelRes = R.string.hint_surname,
                    iconRes = R.drawable.ic_person,
                    onValueChange = {surnameValue = it},
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    isCleaned = isClean
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.common_padding_default))
                ) {
                    //Heigh
                    CrTextField(
                        modifier = Modifier.weight(40f),
                        labelRes = R.string.hint_height,
                        iconRes = R.drawable.ic_height,
                        maxLengthRes = R.integer.height_max_value,
                        minValue = integerResource(R.integer.height_min_value),
                        onValueChange = {heightValue = it},
                        errorRes = R.string.error_min_height_valid,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        isCleaned = isClean
                    )

                    //BirthDate
                    CrTfDate(
                        modifier = Modifier
                            .weight(40f)
                            .padding(top = dimensionResource(R.dimen.common_padding_min)),
                        labelRes = R.string.birth_date,
                        selectedDate = dateValue,
                        onShowModal = {
                            showDatePicker = true
                        }
                    )
                    if (showDatePicker){
                        DatePickerModal(
                            onDismiss = {showDatePicker = false},
                            onDateSelected = {dateValue = it},
                        )
                    }
                }

                //Ocupacion
                Column(
                    Modifier
                        .padding(dimensionResource(R.dimen.common_padding_min))
                        .selectableGroup()
                ) {
                    Text(
                        stringResource(R.string.section_ocupation),
                        style = Typography.labelLarge
                    )
                    profiles.forEach { occupation ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(R.dimen.rb_row_height))
                                .selectable(
                                    selected = (occupation == selectedOption),
                                    onClick = { onOptionSelected(occupation) },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (occupation == selectedOption),
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.secondary
                                ),
                                onClick = null
                            )
                            Text(
                                occupation,
                                modifier = Modifier.padding(start = dimensionResource(R.dimen.common_padding_default))
                            )
                        }
                    }
                }

                //Notes
                CrTextField(
                    labelRes = R.string.hint_notes,
                    iconRes = R.drawable.ic_notes,
                    onValueChange = {notesValue = it},
                    maxLengthRes = R.integer.notes_max_length,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    singleLine = false,
                    isRequired = false,
                    paddingTop = dimensionResource(R.dimen.common_padding_none),
                    isCleaned = isClean
                )

                //Agree
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        stringResource(R.string.checkbox_agree),
                        modifier = Modifier.clickable{
                            isAgree = !isAgree
                        }
                    )
                    Checkbox(
                        checked = isAgree,
                        onCheckedChange = {isAgree = it}
                    )

                }

                //Save
                Button(
                    onClick = {
                        val errors = foundErrors(context, nameValue, surnameValue, heightValue)
                        if (errors == null){
                            val user = User(
                                nameValue,
                                surnameValue,
                                heightValue.toInt(),
                                dateValue ?: 0,
                                selectedOption,
                                notesValue
                            )
                            onSave(user)
                        }else{
                            onError(errors)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.common_padding_default)),
                    shape = CutCornerShape(8.dp),
                    enabled = isAgree
                ) {
                    Icon(painter = painterResource(R.drawable.ic_check), contentDescription = null)
                    Text(stringResource(R.string.btn_register))
                }
            }
        }

        if (false){
            Box(
                Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.progress_background))
                    .clickable {},
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
    }
}