package com.example.restaurantfindkun.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantfindkun.ui.theme.Black
import com.example.restaurantfindkun.ui.theme.Cream
import com.example.restaurantfindkun.ui.theme.Gray
import com.example.restaurantfindkun.ui.theme.SearchText

//
//共通：テキストフィールド
//

@Composable
fun TextFieldContent(
    modifier: Modifier = Modifier,
    textString: String,
    valueChange: (String) -> Unit,
    readAble: Boolean,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                color = Gray,
                shape = RoundedCornerShape(5.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = modifier
                .background(
                    color = Cream,
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(6.dp)
                .weight(8f)
                .fillMaxWidth()
                .clickable { onExpandChange(!expanded) },
            value = textString,
            onValueChange = valueChange,
            readOnly = readAble,
            singleLine = true,
            cursorBrush = SolidColor(Black),
            textStyle = LocalTextStyle.current.copy(
                color = Black,
                fontStyle = SearchText.fontStyle
            )
        )

        if (readAble) {
            IconButton(
                onClick = { onExpandChange(!expanded) },
                modifier = Modifier
                    .size(20.dp)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Icon"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTextFieldContent() {
    TextFieldContent(
        modifier = Modifier,
        textString = "aaaaa",
        valueChange = {},
        readAble = true,
        expanded = false,
        onExpandChange = {}
    )
}