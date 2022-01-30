package com.knightbyte.answers.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.R
import com.knightbyte.answers.presentation.ui.theme.MyPurple500
import com.knightbyte.answers.presentation.ui.theme.MyPurple700
import com.knightbyte.answers.presentation.ui.theme.promptSans

@Composable
fun TotalAnswers(
    total: Int = 0
) {
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .width(140.dp)
            .height(150.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MyPurple500)
                .padding(
                    top = 20.dp,
                    bottom = 20.dp,
                    start = 10.dp,
                    end = 10.dp
                ),
        ) {

            val textStyle = TextStyle(
                color = MyPurple700,
                fontFamily = promptSans
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                text = total.toString(),
                style = textStyle,
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd),
                text = "Total Answer",
                style = textStyle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )

        }

    }
}

@Composable
fun LatestUpdate(
    date: String,
    testName: String
) {
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MyPurple500)
                .padding(
                    top = 20.dp,
                    bottom = 10.dp,
                    start = 10.dp,
                    end = 10.dp
                ),
        ) {

            val textStyle = TextStyle(
                color = MyPurple700,
                fontFamily = promptSans
            )
            Column(Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_upload_icon_svg),
                        contentDescription = null,
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Last Uploaded",
                        style = textStyle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 40.dp
                        ),
                    text = date,
                    style = textStyle,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_double_tick_icon_svg),
                        contentDescription = null,
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Latest Answer",
                        style = textStyle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 40.dp
                        ),
                    text = testName,
                    style = textStyle,
                    fontSize = 14.sp
                )
            }


        }

    }
}

