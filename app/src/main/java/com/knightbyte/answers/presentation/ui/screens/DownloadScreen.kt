package com.knightbyte.answers.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.knightbyte.answers.presentation.components.SingleCard
import com.knightbyte.answers.presentation.ui.theme.MyPurple100
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel

@Composable
fun DownloadScreen(
    navController: NavHostController,
    answersViewModel: AnswersViewModel,
){
    val context = LocalContext.current
    val totalDownload = remember {
        mutableStateOf(0)
    }

//     answersViewModel.appFile.getFiles(context)
    SideEffect {
        answersViewModel.updateDownloadFile()
        totalDownload.value = answersViewModel.appFile.getFileListSize(context)
    }
    val downloadedFiles = answersViewModel.downloadedFile.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MyPurple100)
            .padding(
                top = 20.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Column {
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Downloaded ( ${totalDownload.value} )",
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (downloadedFiles.isNotEmpty()) {
                LazyColumn {
                    item {
                        downloadedFiles.forEach { answer ->
                            SingleCard(
                                fileName = answer,
                                answersViewModel = answersViewModel,
                                delete = true
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

