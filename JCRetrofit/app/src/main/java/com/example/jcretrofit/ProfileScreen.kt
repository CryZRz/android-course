package com.example.jcretrofit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcretrofit.entities.Data
import com.example.jcretrofit.entities.SingleUserResponse
import com.example.jcretrofit.entities.Support
import com.example.jcretrofit.ui.components.CrCoilImage
import com.example.jcretrofit.ui.components.ProgressFullScreen
import com.example.jcretrofit.ui.theme.JCRetrofitTheme
import com.example.jcretrofit.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    JCRetrofitTheme {
        ProfileView(
            Modifier,
            SingleUserResponse(
                Data(1, "chris@mial.com", "adwadw", "adwadw", ""),
                Support("Hola como estas", "Descripcion")
            )
            )
    }
}

@Composable
fun ProfileView(modifier: Modifier, response: SingleUserResponse?, inProgress: Boolean = false){
    Box(modifier){
        response?.let {
            Column(
                Modifier.fillMaxSize()
                    .padding(dimensionResource(R.dimen.common_padding_default)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CrCoilImage(response.data.avatar, R.dimen.img_cover_size)
                Text(
                    response.data.getFullName(),
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.common_padding_default)),
                    style = Typography.headlineSmall
                    )
                Text(
                    response.data.email,
                    style = Typography.bodyLarge
                )
                Text(
                    response.support.text,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.common_padding_default)),
                    style = Typography.bodyLarge
                )
                Spacer(Modifier.weight(1f))
                Text(buildAnnotatedString {
                    withLink(LinkAnnotation.Url(
                        url = response.support.url,
                        styles = TextLinkStyles(style = SpanStyle(color = Color.Blue))
                    )){
                        append(response.support.url)
                    }
                },
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.common_padding_min)),
                    )
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