package com.example.jcroom

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.example.jcroom.room.entities.Insect
import com.example.jcroom.ui.components.CrCoilImage
import com.example.jcroom.ui.components.CrTextField
import com.example.jcroom.ui.components.ProgressFullScreen
import com.example.jcroom.ui.theme.Typography

@Composable
fun AddView(
    modifier: Modifier,
    inProgress: Boolean = false,
    onSave: (Insect) -> Unit
){
    var nameValue by remember { mutableStateOf("") }
    var imageValue by remember { mutableStateOf("") }

    var context = LocalContext.current

    val galleryResult = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ){ uri ->
        if (uri != null){
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, flag)
            imageValue = uri.toString()
        }
    }

    Box(
        modifier = modifier
    ){
        Column(
            modifier.padding(dimensionResource(R.dimen.common_padding_default)),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                stringResource(R.string.add_title),
                style = Typography.headlineMedium
            )

            CrCoilImage(
                image = imageValue,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.add_insect_img_size))
                    .clickable{
                        galleryResult.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
            )
            CrTextField(
                labelRes = R.string.hint_name,
                iconRes = Icons.Default.BugReport,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                onValueChange = {nameValue = it}
            )
            Button(
                onClick = {
                    onSave(Insect(
                        name = nameValue,
                        imgLocation = imageValue
                    ))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.common_padding_default))
            ) {
                Icon(Icons.Default.SaveAlt, contentDescription = null)
                Text(stringResource(R.string.add_btn_save))
            }
        }


        AnimatedVisibility(
            visible = inProgress,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ProgressFullScreen()
        }
    }
}