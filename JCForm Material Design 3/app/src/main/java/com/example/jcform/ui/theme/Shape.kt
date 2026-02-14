package com.example.jcform.ui.theme

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

val crShapes = Shapes(
    extraSmall = CutCornerShape(8.dp), //default RoundedCornerShape()
    small = CutCornerShape(12.dp),
    medium = CutCornerShape(16.dp),
    large = CutCornerShape(20.dp),
    extraLarge = CutCornerShape(24.dp),
    )