@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.jccomponents

import android.content.Context
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.jccomponents.ui.theme.JCComponentsTheme
import java.net.URL

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ContentPreview(){
    JCComponentsTheme {
        ContentView(Modifier) { _,_,_ -> {}}
    }
}

@Composable
fun ContentView(modifier: Modifier, onContentEvent: (String?, Boolean?, Boolean?) -> Unit){
    var colorMain by remember { mutableStateOf(Color.Transparent) }

    Column(modifier
        .verticalScroll(rememberScrollState())
        .background(colorMain)
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.common_padding_min))
        ) {
            ConstraintLayout(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(
                            R.dimen.common_padding_default
                        ),
                        end = dimensionResource(R.dimen.common_padding_default),
                        top = dimensionResource(R.dimen.common_padding_default)
                    )
            ) {
                val (imgCard, tvTitle, tvDescription, btnBuy, btnSkip) = createRefs()


                val image = ContextCompat.getDrawable(LocalContext.current,R.drawable.dave)
                Image(
                    bitmap = image!!.toBitmap().asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(imgCard) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                        .size(dimensionResource(R.dimen.image_size))
                        .background(Color.Cyan)
                )
                val paddingDefault = dimensionResource(R.dimen.common_padding_default)
                Text(stringResource(R.string.title_black_friday),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.constrainAs(tvTitle){
                        start.linkTo(imgCard.end, margin = paddingDefault)
                        end.linkTo(parent.end)
                        top.linkTo(imgCard.top)
                        width = Dimension.fillToConstraints
                    }
                )
                Text(stringResource(R.string.large_description),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.constrainAs(tvDescription){
                        start.linkTo(tvTitle.start)
                        end.linkTo(tvTitle.end)
                        top.linkTo(tvTitle.bottom)
                        bottom.linkTo(imgCard.bottom)
                        width = Dimension.fillToConstraints
                    }
                )
                var buyMessage = stringResource(R.string.btn_buy)
                Button(
                    onClick = {
                        onContentEvent(buyMessage, null, null)
                    },
                    modifier = Modifier.constrainAs(btnBuy){
                        end.linkTo(parent.end)
                        top.linkTo(tvDescription.bottom)
                    }
                    ) {
                    Icon(Icons.Default.Shop, contentDescription = null)
                    Text(buyMessage)
                }
                TextButton(
                    onClick = {},
                    modifier = Modifier.constrainAs(btnSkip){
                        end.linkTo(btnBuy.start)
                        top.linkTo(btnBuy.top)
                    }
                ) {
                    Text(stringResource(R.string.btn_skip))
                }
            }
        }

        var urlValue by remember { mutableStateOf("https://www.copahost.com/blog/wp-content/uploads/2019/07/imgsize2.png") }

        Card(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.common_padding_moddle))
        ) {
            ConstraintLayout(
                Modifier.fillMaxWidth()
            ) {
                val (imgUrl, cForm) = createRefs()

                GlideImage(
                    model = urlValue,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(imgUrl) {
                            top.linkTo(parent.top)
                        }
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.img_cover_height))
                        .background(colorResource(R.color.blue_200)),
                    loading = placeholder(R.drawable.ic_timmer),
                    failure = placeholder(R.drawable.ic_broken_image),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    Modifier
                        .constrainAs(cForm) {
                            top.linkTo(imgUrl.bottom)
                        }
                        .padding(dimensionResource(R.dimen.common_padding_default))
                ) {
                    Text(stringResource(R.string.title_black_friday),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                    var emailValue by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = emailValue,
                        onValueChange = {
                            emailValue = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        label = {
                            Text(stringResource(R.string.hint_email))
                        },
                        trailingIcon = {
                            if (emailValue.isNotEmpty()){
                                Icon(
                                    Icons.Default.Clear,
                                    contentDescription = null,
                                    modifier = Modifier.clickable{
                                        emailValue = ""
                                    }
                                )
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    OutlinedTextField(
                        value = urlValue,
                        onValueChange = {
                            urlValue = it
                        },
                        singleLine = true,
                        label = {
                            Text(stringResource(R.string.hint_email))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.common_padding_default)),
                        trailingIcon = {
                            if (urlValue.isNotEmpty()){
                                Icon(
                                    Icons.Default.Clear,
                                    contentDescription = null,
                                    modifier = Modifier.clickable{
                                        urlValue = ""
                                    }
                                )
                            }
                        },
                        isError = urlValue.isNotEmpty() && !URLUtil.isValidUrl(urlValue),
                        supportingText = {
                            Text(
                                if(urlValue.isNotEmpty() && !URLUtil.isValidUrl(urlValue)) stringResource(R.string.error_invalid_url)
                                else stringResource(R.string.helper_text_required)
                            )
                        }
                    )
                    var passwordValue by remember { mutableStateOf("") }
                    var isPasswordVisible by remember { mutableStateOf(false) }
                    var isCheckboxCheck by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        value = passwordValue,
                        onValueChange = {passwordValue = it},
                        label = {
                            Text(stringResource(R.string.hint_password))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(R.dimen.common_padding_default),
                                end = dimensionResource(R.dimen.common_padding_default)
                            ),
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                modifier = Modifier.clickable{
                                    isPasswordVisible = !isPasswordVisible
                                }
                            )
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        enabled = isCheckboxCheck
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isCheckboxCheck,
                            onCheckedChange = {isCheckboxCheck = it}
                        )
                        Text(stringResource(R.string.cb_enable_passowrd))

                        Spacer(Modifier.weight(1f))

                        var isSwitchChecked by remember { mutableStateOf(true) }
                        Text(text = stringResource(if (isSwitchChecked) R.string.sw_hide_fab else R.string.sw_show_fab))
                        Switch(checked = isSwitchChecked, onCheckedChange = { currentValue ->
                            isSwitchChecked = currentValue
                            onContentEvent(null, isSwitchChecked, null)
                        })
                    }
                    var chipVisible by remember { mutableStateOf(true) }
                    var selectedChip by remember { mutableStateOf(false) }
                    var chipValue by remember { mutableStateOf("cryz@gmail.com") }
                    var context = LocalContext.current

                    if (chipVisible){
                        InputChip(
                            selected = selectedChip,
                            label = {
                                Text(chipValue)
                            },
                            onClick = {
                                selectedChip = !selectedChip
                                Toast.makeText(context, chipValue, Toast.LENGTH_SHORT).show()
                            },
                            avatar = if(selectedChip) {
                                { Icon(
                                    Icons.Default.Done,
                                    contentDescription = null,
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                ) }
                            }else{
                                { null }
                            },
                            trailingIcon = {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = null,
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        .clickable{
                                            chipVisible = false
                                        }
                                )
                            }
                        )
                    }
                    var sliderValue by remember { mutableStateOf(7f) }
                    Slider(
                        value = sliderValue,
                        onValueChange = {
                            sliderValue = it
                            chipValue = context.getString(R.string.sld_value, sliderValue) },
                        valueRange = 0f..10f,
                        steps = 9,
                        onValueChangeFinished = {
                            chipValue = context.getString(R.string.sld_value, sliderValue)
                        }
                    )

                    HorizontalDivider(
                        Modifier.fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.common_padding_min))
                    )


                    Text(stringResource(R.string.title_black_friday),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(stringResource(R.string.large_description),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )

                    val colors = listOf(stringResource(R.string.btnred), stringResource(R.string.btn_green), stringResource(R.string.btn_blue))
                    var selectIndex by remember { mutableStateOf(-1) }
                    colorMain = when(selectIndex){
                        0 -> Color.Red
                        1 -> Color.Green
                        2 -> Color.Blue
                        else -> Color.Transparent
                    }

                    SingleChoiceSegmentedButtonRow(
                        Modifier.padding(top = 16.dp)
                    ) {
                        colors.forEachIndexed { index, label ->
                            SegmentedButton(shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = colors.size
                            ),
                                onClick = {
                                    selectIndex = index

                                },
                                selected = index == selectIndex,
                                label = {
                                    Text(label)
                                }
                                )
                        }
                    }
                    var count by remember { mutableStateOf(21) }

                    BadgedBox(
                        modifier = Modifier.padding(16.dp),
                        badge = {
                            if (count > 0){
                                Badge{
                                    Text("$count")
                                }
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null,
                            modifier = Modifier.clickable{
                                onContentEvent(null, null, true)
                            }
                        )
                    }
                    Box(
                        Modifier.fillMaxWidth(),
                        Alignment.CenterEnd
                        ){
                        Button(
                            onClick = {count++},
                            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.common_padding_default))
                            ) {
                            Text(stringResource(R.string.common_ok))
                        }
                    }
                }
            }
        }
    }
}