package com.example.jcretrofit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcretrofit.entities.Data
import com.example.jcretrofit.ui.components.CrCoilImage
import com.example.jcretrofit.ui.theme.JCRetrofitTheme
import com.example.jcretrofit.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun ItemUserPreview(){
    JCRetrofitTheme {
        ItemUserView(getUser().data)
    }
}


@Composable
fun ItemUserView(user: Data){
    Box(
        contentAlignment = Alignment.BottomCenter
    ){
        Row(
            Modifier.fillMaxWidth()
                .padding(
                    vertical = dimensionResource(R.dimen.common_padding_min),
                    horizontal = dimensionResource(R.dimen.common_padding_min)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CrCoilImage(
                image = user.avatar,
                sizeRes = R.dimen.img_list_size
            )
            Text(
                user.getFullName(),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.common_padding_min)),
                style = Typography.titleLarge
            )
        }
        HorizontalDivider()
    }
}