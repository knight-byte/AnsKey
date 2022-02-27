package com.knightbyte.answers.presentation.ui.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.presentation.components.CategoryChip
import com.knightbyte.answers.presentation.components.LatestUpdate
import com.knightbyte.answers.presentation.components.SingleCard
import com.knightbyte.answers.presentation.components.TotalAnswers
import com.knightbyte.answers.presentation.ui.theme.MyPurple100
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel
import com.knightbyte.answers.utils.CUSTOM_ERROR_DEBUG_LOG
import com.knightbyte.answers.utils.DRIVE_API_BASE_URL
import com.knightbyte.answers.utils.Resource
import java.io.File

@Composable
fun HomeScreen(
    navController: NavHostController,
    answersViewModel: AnswersViewModel
) {
    val allfiles = answersViewModel.allFiles.value
    var total: Int? = null
    var rawAnswers: List<TestFile>? = null
    val query = answersViewModel.homeCategory.value
    when (allfiles) {
        is Resource.Success -> {
            total = allfiles.data?.size
            rawAnswers = allfiles.data
        }
        is Resource.Error -> {
            allfiles.message?.let { Log.d(CUSTOM_ERROR_DEBUG_LOG, it) }
        }
        else -> {
        }
    }


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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TotalAnswers(total = total)
                Spacer(modifier = Modifier.width(10.dp))
                LatestUpdate("30 Jan, 2022", "Examly Level 5")
            }
            Spacer(modifier = Modifier.height(10.dp))


            // Static for testing ( WIP )
            val category = listOf(
                "All",
                "Examly",
                "HBE",
                "InfyQt",
                "Misc",
            )
            LazyRow {
                item {
                    category.forEach { cat ->
                        val isSelected = (query == cat || (query == "" && cat == "All"))
                        CategoryChip(cat, isSelected, answersViewModel)
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            if (rawAnswers != null) {
                val answers = answersViewModel.searchFiles(query)
                LazyColumn {
                    item {
                        answers.forEach { answer ->

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
            } else {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
