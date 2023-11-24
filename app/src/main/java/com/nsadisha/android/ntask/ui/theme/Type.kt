package com.nsadisha.android.ntask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.nsadisha.android.ntask.R

val comicNeueFamily = FontFamily(
    Font(R.font.comic_neue_light, FontWeight.Light),
    Font(R.font.comic_neue_regular, FontWeight.Normal),
    Font(R.font.comic_neue_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = black
    ),
    titleLarge = TextStyle(
        fontSize = 48.sp,
        lineHeight = 48.sp,
        fontFamily = comicNeueFamily,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 17.6.sp,
        fontFamily = comicNeueFamily,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
    ),
    labelMedium = TextStyle(
        fontSize = 20.sp,
        lineHeight = 20.sp,
        fontFamily = comicNeueFamily,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)