package com.example.jcadapters.ui.components

import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcadapters.R
import com.example.jcadapters.ui.theme.JCAdaptersTheme
import com.example.jcadapters.ui.theme.Typography
import androidx.compose.material3.ListItem
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Preview(showBackground = true)
@Composable
private fun BasicPreview(){
    JCAdaptersTheme {
        ItemListBase("some text")
    }
}

@Preview(showBackground = true)
@Composable
private fun AdvancedPreview(){
    JCAdaptersTheme {
        ItemListAdvanced(mainText = "Some main text...", secondaryText = "more text, more text", icon = null, showDivider = true)
    }
}

@Composable
fun ItemListBase(text: String, modifier: Modifier = Modifier){
    Column {
        Text(
            text,
            modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.common_padding_default)),
            style = Typography.headlineSmall
        )
        HorizontalDivider()
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemListAdvanced(
    modifier: Modifier = Modifier,
    mainText: String,
    secondaryText: String,
    imgUrl: String = "",
    icon: ImageVector?,
    overLineText: String = "",
    showDivider: Boolean
){
    Column {
        ListItem(
            modifier = modifier,
            headlineContent = {
                Text(
                    mainText,
                    style = Typography.headlineMedium
                )
            },
            supportingContent = {
                Text(
                    secondaryText,
                    style = Typography.headlineLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            },
            leadingContent = {
                GlideImage(
                    model = imgUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(dimensionResource(R.dimen.item_list_img_size))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.common_padding_default)))
                        .border(
                            BorderStroke(
                                width = dimensionResource(R.dimen.common_padding_nano),
                                color = colorResource(R.color.pink)
                            ),
                            RoundedCornerShape(dimensionResource(R.dimen.common_padding_default))
                        )
                )
            },
            trailingContent = {
                icon?.let {
                    Icon(icon, contentDescription = null)
                }
            },
            overlineContent = {
                if(!overLineText.isEmpty()) Text(overLineText)
            }
        )
        if(showDivider) HorizontalDivider()
    }
}