package com.ramoncinp.androidautopresentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = normalTextSize,
        color = Color.White
    )
)

val placeHolderStyle = TextStyle(
    color = Color.Gray,
    fontSize = normalTextSize
)
