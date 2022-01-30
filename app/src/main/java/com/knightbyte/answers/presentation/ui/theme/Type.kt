package com.knightbyte.answers.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val promptSans = FontFamily(
    Font(
        R.font.promt_light,
        FontWeight.Light
    ),
    Font(
        R.font.prompt_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.prompt_medium,
        FontWeight.Medium
    ),
    Font(
        R.font.prompt_semi_bold,
        FontWeight.SemiBold
    ),
    Font(
        R.font.promt_bold,
        FontWeight.Bold
    )
)

val promptTypo = Typography(
    body1 = TextStyle(
        fontFamily = promptSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)