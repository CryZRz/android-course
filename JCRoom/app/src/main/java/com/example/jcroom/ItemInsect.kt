package com.example.jcroom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcroom.room.entities.Insect
import com.example.jcroom.ui.components.CrCoilImage
import com.example.jcroom.ui.theme.Typography

@Preview
@Composable
fun ItemInsectPreview(){
    ItemInsectView(insect = getInsectPreview())
}

@Composable
fun ItemInsectView(insect: Insect, modifier: Modifier = Modifier){
    Box(
        modifier,
        contentAlignment = Alignment.BottomCenter
        ){
        CrCoilImage(
            insect.imgLocation,
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            shape = RectangleShape
            )
        Text(
            insect.name,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.common_padding_default)),
                color = if (insect.inDanger) Color.Red else Color.White,
                style = TextStyle(
                    fontSize = Typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        color = if (insect.inDanger) Color.Yellow else Color.Black,
                        offset = Offset(0f, 2f),
                        blurRadius = 6f
                    )
                )
        )
    }
}