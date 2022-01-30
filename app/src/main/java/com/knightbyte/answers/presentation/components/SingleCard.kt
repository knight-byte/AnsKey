package com.knightbyte.answers.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.knightbyte.answers.presentation.ui.theme.MyPurple500
import com.knightbyte.answers.presentation.ui.theme.MyPurple700
import com.knightbyte.answers.presentation.ui.theme.promptSans

@Composable
fun SingleCard(
    title: String = "Examly Level - 1",
    testName: String = "Test 1",
    DownloadIcon: Boolean = true,
    DownloadAction: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MyPurple500)
                .padding(
                    start = 30.dp,
                    end = 30.dp,
                ),
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_card_icon_svg),
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(20.dp))
                val textStyle = TextStyle(
                    color = MyPurple700,
                    fontFamily = promptSans
                )
                Column {
                    Text(
                        text = title,
                        style = textStyle,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = testName,
                        style = textStyle,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            if (DownloadIcon) {
                Image(
                    painter = painterResource(R.drawable.ic_download2_icon_svg),
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = DownloadAction),
                    contentDescription = "",
                )
            }
        }

    }
}
