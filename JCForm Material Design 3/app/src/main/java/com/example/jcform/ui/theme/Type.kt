package com.example.jcform.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jcform.R

@OptIn(ExperimentalTextApi::class)
val bodyFontFamily = FontFamily(
    Font(
        R.font.merriweather,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(750)
        ))
)

@OptIn(ExperimentalTextApi::class)
val flexFontFamily = FontFamily(
    Font(
        R.font.roboto_italic,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(750),
            FontVariation.width(550f),
            FontVariation.slant(-30f)
        )
    )
)

val titleLargeFontFamily = FontFamily(
    Font(R.font.merriweather_italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = flexFontFamily,
        fontSize = 10.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = flexFontFamily,
    ),
    bodyLarge = TextStyle(
        fontFamily = bodyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = flexFontFamily,
    ),
    titleLarge = TextStyle(
        fontFamily = titleLargeFontFamily,
        fontSize = 28.sp,
        letterSpacing = 0.sp
    )
)