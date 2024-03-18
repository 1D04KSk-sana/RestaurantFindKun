package com.example.restaurantfindkun.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import com.example.restaurantfindkun.ui.theme.Black
import com.example.restaurantfindkun.ui.theme.DropdownMenuText
import com.example.restaurantfindkun.ui.theme.White

@Composable
fun TextFieldContent(
    modifier: Modifier = Modifier,
    textString: String,
) {
    var text by rememberSaveable { mutableStateOf("") }
    BasicTextField(modifier = modifier
        .background(
            Black,
        )
        .fillMaxWidth(),
        value = textString,
        onValueChange = {
            text = it
        },
        singleLine = true,
        cursorBrush = SolidColor(Black),
        textStyle = LocalTextStyle.current.copy(
            color = Black,
            fontStyle = DropdownMenuText.fontStyle
        )
    )
}

@Preview
@Composable
fun PreviewTextFieldContent() {
    TextFieldContent(
        modifier = Modifier,
        textString = "aaaaa"
    )
}