package com.example.restaurantfindkun.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantfindkun.ui.theme.Black
import com.example.restaurantfindkun.ui.theme.White

@Composable
fun DropDownMenuContent(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down Icon"
                    )
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .border(
                    width =2.dp,
                    color = Black,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable { expanded = !expanded } // クリックで展開
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onItemSelected(item)
                    expanded = false
                }) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
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