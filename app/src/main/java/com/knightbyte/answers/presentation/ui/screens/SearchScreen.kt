package com.knightbyte.answers.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.knightbyte.answers.presentation.components.SearchBar
import com.knightbyte.answers.presentation.ui.theme.MyPurple100

@Composable
fun SearchScreen(
navController: NavHostController
){
    var textState = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MyPurple100)
            .padding(
                top = 20.dp,
                start = 20.dp,
                end = 20.dp
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    })
            }
    ) {
        Column {
            SearchBar(textState)

        }
    }

}