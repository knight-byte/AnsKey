package com.knightbyte.answers.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.knightbyte.answers.utils.Resource

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
                "Amcat",
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
                            val title = "${answer.testType} - ${answer.testName}"
                            SingleCard(
                                title = title,
                                testName = answer.testLevel,
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}
