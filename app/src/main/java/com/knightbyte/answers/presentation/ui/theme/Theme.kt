package com.knightbyte.answers.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = MyPurple200,
    primaryVariant = MyPurple700,
    secondary = MyPurple100
)

private val LightColorPalette = lightColors(
    primary = MyPurple200,
    primaryVariant = MyPurple700,
    secondary = MyPurple500

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AnswersTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController
            .setStatusBarColor(
                color = MyPurple100
            )
        systemUiController
            .setNavigationBarColor(
                color = MyPurple200
            )
    }

    MaterialTheme(
        colors = colors,
        typography = promptTypo,
        shapes = Shapes,
        content = content
    )
}