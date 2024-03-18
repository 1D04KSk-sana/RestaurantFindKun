package com.example.restaurantfindkun.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantfindkun.ui.theme.DropdownMenuText

@Composable
fun DropDownMenuContent(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)
    ) {
        TextFieldContent(
            textString = selectedItem,
            valueChange = {},
            readAble = true,
            expanded = expanded,
            onExpandChange = { expanded = it },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                    onItemSelected(item)
                    expanded = false
                },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item,
                        style = DropdownMenuText,
                        modifier = Modifier
                            .height(40.dp)
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}

//
//プレビュー：ドロップダウンメニュー
//
@Preview
@Composable
fun PreviewDropdownMenuContent() {
    val testItems = listOf("1", "2", "3", "4")
    var selectedItem by remember { mutableStateOf(testItems.first()) }

    DropDownMenuContent(
        items = testItems,
        selectedItem = selectedItem,
        onItemSelected = { selectedItem = it }
    )
}