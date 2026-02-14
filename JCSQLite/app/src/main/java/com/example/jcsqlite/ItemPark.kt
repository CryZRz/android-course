package com.example.jcsqlite

import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcsqlite.ui.components.CrCheckBox
import com.example.jcsqlite.ui.theme.JCSQLiteTheme
import com.example.jcsqlite.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun ItemParkPreview(){
    JCSQLiteTheme {
        ItemParkView(parkPreview, {}, {})
    }
}

@Composable
fun ItemParkView(park: Park, onClick: (Park) -> Unit, onLongClick: (Park) -> Unit){

    var isFavorite by remember { mutableStateOf(park.isFav) }

    Row(
        Modifier.fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.common_padding_min))
            .combinedClickable(
                onLongClick = {onLongClick(park)},
                onClick = {}
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_park),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(R.color.purple_200))
            )
        Text(
            park.name,
                modifier = Modifier.weight(1f)
                    .padding(horizontal = dimensionResource(R.dimen.common_padding_min)),
            style = Typography.headlineSmall
            )
        CrCheckBox(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = it
                park.isFav = isFavorite
                onClick(park)
            }
        )

    }
}