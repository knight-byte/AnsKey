package com.knightbyte.answers.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.presentation.ui.theme.MyPurple200
import com.knightbyte.answers.presentation.ui.theme.MyPurple500
import com.knightbyte.answers.presentation.ui.theme.MyPurple700
import com.knightbyte.answers.presentation.ui.theme.promptSans
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel


@Composable
fun CategoryChip(
    title: String = "All",
    isSelected: Boolean = false,
    answersViewModel: AnswersViewModel
) {
    val background = if (isSelected) MyPurple500 else MyPurple200

    val onClick = {
        if (title == "All"){
            answersViewModel.homeCategory.value = ""
        } else {
            answersViewModel.homeCategory.value = title
        }
    }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .height(40.dp)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val textStyle = TextStyle(
                color = MyPurple700,
                fontFamily = promptSans
            )
            val space = 30.dp
            Spacer(modifier = Modifier.width(space))
            Text(
                text = title,
                style = textStyle,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(space))
        }
    }
}
