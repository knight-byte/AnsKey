package com.knightbyte.answers.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.R
import com.knightbyte.answers.presentation.ui.theme.MyPurple150
import com.knightbyte.answers.presentation.ui.theme.MyPurple700

@Composable
fun SearchBar(
    text: MutableState<String>,
) {

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        val isTextFieldFocus = remember {
            mutableStateOf(false)
        }
        TextField(

            modifier = Modifier
                .fillMaxSize()
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MyPurple150,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = text.value,
            onValueChange = { value ->
                text.value = value
            },
            label = {
                val fontSize: TextUnit = if (isTextFieldFocus.value || text.value.isNotEmpty()) {
                    12.sp
                } else {
                    18.sp
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
                    painterResource(R.drawable.ic_search2_icon_svg),
                    contentDescription = "Search Bar",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp),
                    tint = MyPurple700
                )
            },
            singleLine = true,
        )
    }
}
