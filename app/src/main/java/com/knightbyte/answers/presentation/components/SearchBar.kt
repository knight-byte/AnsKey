package com.knightbyte.answers.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.presentation.ui.theme.MyPurple200

@Composable
fun SearchBar (
    text: MutableState<TextFieldValue>,
    onSearch: (String) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        TextField(
            value = text.value,
            onValueChange = { value ->
                text.value = value
            },
            label = {
                Text(
                    text = "Search",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MyPurple200),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Bar",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp),
                    tint = Color.Black
                )
            },
            singleLine = true,
        )
    }
}

//@Preview
//@Composable
//fun Preview() {
//    val textState = remember { mutableStateOf(TextFieldValue("Search...")) }
//    SearchBar(textState)
//}