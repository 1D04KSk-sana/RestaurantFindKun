package com.example.restaurantfindkun.screen.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.restaurantfindkun.R
import com.example.restaurantfindkun.ui.theme.Black

@Composable
fun ShowDialog(onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.approval),
                        color = Black
                    )
                }
            },
            title = {
                Text(stringResource(R.string.error_search_title))
            },
            text = {
                Text(stringResource(R.string.error_search_message))
            }
        )
}
