package com.example.restaurantfindkun.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantfindkun.ui.theme.Black
import com.example.restaurantfindkun.ui.theme.SearchText

//
//共通：チェックボックス
//
@Composable
fun CheckBoxContent(
    titleName: String,
    onCheckedChange: (Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(false) }
    Row(
        Modifier
            .clickable(onClick = { checkedState.value = !checkedState.value })
            .padding(start = 16.dp, top = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = titleName,
            style = SearchText,
            color = Black,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onCheckedChange(checkedState.value)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GPreviewCheckBoxContent() {
    CheckBoxContent("ジャンル名", {})
}