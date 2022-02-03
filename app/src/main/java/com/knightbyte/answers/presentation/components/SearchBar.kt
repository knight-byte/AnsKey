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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.presentation.ui.theme.MyPurple200

@Composable
fun SearchBar(
    text: MutableState<String>,
) {

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        val isTextFieldFocus = remember {
            mutableStateOf(false)
        }
        TextField(
            value = text.value,
            onValueChange = { value ->
                text.value = value
            },

            modifier = Modifier
                .fillMaxWidth()
                .background(MyPurple200)
                .onFocusChanged { focusState ->
                    when {
                        focusState.hasFocus -> {
                            isTextFieldFocus.value = true
                        }
                        else -> {
                            isTextFieldFocus.value = false
                        }
                    }
                },
            label = {
                val fontSize: TextUnit = if (isTextFieldFocus.value || text.value.isNotEmpty()) {
                    12.sp
                } else {
                    16.sp
                }
                Text(
                    text = "Search",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = fontSize
                    ),
                )
            },

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
