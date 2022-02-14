package com.knightbyte.answers.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.knightbyte.answers.presentation.components.SearchBar
import com.knightbyte.answers.presentation.components.SingleCard
import com.knightbyte.answers.presentation.ui.theme.MyPurple100
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    answersViewModel: AnswersViewModel
) {
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
        val result = answersViewModel.searchFiles(textState.value)
        Column {
            SearchBar(textState)
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                text = "Results ( ${result.size} )",
                fontSize = 20.sp,
                color = Color.Black

            )
            Spacer(modifier = Modifier.height(15.dp))
            if (result.isNotEmpty()) {
                LazyColumn {
                    item {
                        result.forEach { answer ->
                            SingleCard(
                                fileName = answer.fileName,
                                fileId = answer.fileId,
                                answersViewModel = answersViewModel
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }
            }
        }
    }

}