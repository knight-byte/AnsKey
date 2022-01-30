package com.knightbyte.answers.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.knightbyte.answers.presentation.components.CategoryChip
import com.knightbyte.answers.presentation.components.LatestUpdate
import com.knightbyte.answers.presentation.components.SingleCard
import com.knightbyte.answers.presentation.components.TotalAnswers
import com.knightbyte.answers.presentation.ui.theme.MyPurple100

@Composable
fun HomeScreen(
    navController: NavHostController ?=null
){
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
            // Test

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TotalAnswers()
                Spacer(modifier = Modifier.width(10.dp))
                LatestUpdate("30 Jan, 2022","Examly Level 5")
            }
            Spacer(modifier = Modifier.height(10.dp))

            val category = listOf(
                Pair("All",true),
                Pair("Examly",false),
                Pair("HBE",false),
                Pair("InfyQt",false),
                Pair("Amcat",false),
            )
            LazyRow {
                item {
                    category.forEach { cat ->
                        CategoryChip(cat.first, cat.second)
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn {
                item {
                    for (i in 1..10) {
                        SingleCard()
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun prevHome(){
    HomeScreen()

}
