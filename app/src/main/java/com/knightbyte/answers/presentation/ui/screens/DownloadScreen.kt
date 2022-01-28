package com.knightbyte.answers.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DownloadScreen(
    navController: NavHostController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(
            text = "DOWNLOAD",
            fontSize = 30.sp
            )
    }
}
