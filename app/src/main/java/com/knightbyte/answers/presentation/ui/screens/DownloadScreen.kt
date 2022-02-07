package com.knightbyte.answers.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.knightbyte.answers.network.cache.AppFiles
import com.knightbyte.answers.presentation.components.SingleCard
import com.knightbyte.answers.presentation.ui.theme.MyPurple100
import com.knightbyte.answers.presentation.ui.theme.MyPurple200
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel
import java.time.format.TextStyle

@Composable
fun DownloadScreen(
    navController: NavHostController,
    answersViewModel: AnswersViewModel,

){
    val context = LocalContext.current
    val downloadedFiles = answersViewModel.appFile.getFiles(context)
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
//            Text(
//                text = "Directory = ${answersViewModel.appFile.getCurDir(context)} ",
//                fontSize = 18.sp,
//                color = Color.Black
//            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Downloaded ( ${answersViewModel.appFile.getFilesList(context)} )",
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (downloadedFiles.isNotEmpty()) {
                LazyColumn {
                    item {
                        downloadedFiles.forEach { answer ->
                            val fileName = answer
                            val tempSplit= fileName.split("_")
                            val testType = tempSplit[0]
                            var testName = tempSplit[1]
                            var testLevel = tempSplit[2].split(".")[0]
                            val title = "${testType} - ${testName}"
                            SingleCard(
                                title = title,
                                testName = testLevel,
                                DownloadIcon = false
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
