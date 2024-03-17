package com.example.restaurantfindkun.screen.top

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Welcome to Top Screen!",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}

//
//メイン画面の要素
//
@Composable
fun TopScreenContent() {
    Text(text = "プロフィール")
}

//
//プレビュー：メイン画面の要素
//
@Preview
@Composable
fun PreviewMainScreen() {
    TopScreenContent()
}